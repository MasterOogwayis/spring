package com.zsw.mq.service;

import com.zsw.base.utils.RandomCodeUtils;
import com.zsw.mq.base.BaseMqMessage;
import com.zsw.mq.cache.MessageWrapper;
import com.zsw.mq.cache.MessageWrapperCache;
import com.zsw.mq.persistence.domain.MessageAddress;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * 消息发送服务 - 只处理了简单消息，无有序消息相关逻辑
 *
 * @author ZhangShaowei on 2018/3/14 10:20
 **/
@Service
public abstract class MQHandlerService {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     */
    @Value("${spring.application.name}")
    private String appName;


    /**
     * 重试次数
     */
    private static final int RETRY_TIMES = 3;

    /**
     * 重试等待时间 10ms
     */
//    private static final long RETRY_WAIT_TIME = 10L;

    /**
     * 失败队列
     */
    private static final String SEND_FAIL_QUEUE = "mq:fail:queue";

    /**
     * 待发消息 prefix
     */
    private static final String MESSAGE_CACHE_KEY_PREFIX = "mq:waiting:cache";

    /**
     * 标记为发送失败的消息 - 待发表中 超过 30S未发送的 标记为失败
     * 程序启动时写入到失败队列
     */
    private static final long SEND_FAIL_TIME = 30 * 1000L;

    /**
     * 消息暂存时间
     */
    private static final long TIME_OUT = 3;

    /**
     * 消息暂存时间单位
     */
    private static final TimeUnit TIME_UNIT = TimeUnit.DAYS;

    /**
     * 定制的 消息缓存 带泛型
     */
    @Resource(name = "messageWrapperRedis")
    private MessageWrapperCache cache;

    /**
     * MessageAddressService
     */
    @Autowired
    private MessageAddressService messageAddressService;

    /**
     * 线程池  classpath: config/spring/app-context-threadpool.xml
     */
    @Resource(name = "threadPoolTaskExecutor")
    private Executor executor;


    /**
     * 自定义事物消息，当前事物成功提交后发送
     *
     * @param message BaseMqMessage
     */
    public void transactionSimpleMessage(final BaseMqMessage message) {
        this.logger.debug("发送自定义事务消息: {}", message);

        // step.1 暂存消息，分配一个随机的key,消息发送成功后删除
        final String key = this.getCacheKey(RandomCodeUtils.randomCode());
        // 消息主体存 redis - 暂存时间为 3 天
        this.cache.set(key, new MessageWrapper(key, message), TIME_OUT, TIME_UNIT);
        // 地址存 body 放数据库 - 回查消息若找不到此条消息 则为废弃消息 不予发送
        this.messageAddressService.add(key, message);

        // step.2 创建当前事务监听器 在当前事物提交后发送消息
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            /**
             * 事物成功提交后 发送
             * 这是个阻塞方法 需异步
             */
            @Override
            public void afterCommit() {
                // 这里无法使用 AopContext.currentProxy() 获取当前代理对象
                // ThreadLocal中的对象是调用此 service 的上级对象
//                SpringContextUtils.getBean("mQHandlerService", MQHandlerService.class)
//                        .sendMessageAsync(message, address, key);
//                sendMessageAsync(message, key);
            }

            @Override
            public void afterCompletion(int status) {
                if (STATUS_COMMITTED == status) {
                    sendMessageAsync(message, key);
                } else { // STATUS_ROLLED_BACK STATUS_UNKNOWN
                    // 事务提交失败/未知 尝试删除暂存消息
                    cache.del(key);
                }
            }
        });

    }

    /**
     * 异步发送消息
     * <p>
     *
     * @param message BaseMqMessage
     * @param key     key
     */
    private void sendMessageAsync(final BaseMqMessage message, final String key) {
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                // 发送消息
                if (sendSimpleMessageRetry(message)) {
                    // 成功后删除消息地址
                    messageAddressService.delAddress(key);
                } else {
                    // 失败后进入失败队列
                    cache.leftPush(getQueueKey(), new MessageWrapper(key, message));
                    logger.error("消息发送失败，进入失败队列：{}，body={}", getQueueKey(), message);
                }
                cache.del(key);
            }
        });
    }

    /**
     * 重试
     *
     * @param message BaseMqMessage
     * @return true or false
     */
    private boolean sendSimpleMessageRetry(BaseMqMessage message) {
        int times = 0;
        while (times < RETRY_TIMES) {
            times++;
            try {
                this.sendSimpleMessage(message);
                return true;
            } catch (Exception e) {
                this.logger.warn("第 {} 次消息发送失败，body={}", times, message);
            }
        }
        return false;
    }

    /**
     * 暂存消息补偿 - 只处理 当前应用队列里的简单消息
     */
    @PostConstruct
    public void sendFailFix() {
        // 这是 所有暂存消息 key
        Set<String> keys = this.cache.keys(this.getCacheKey("*"));
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        for (String key : keys) {
            // 超过 30s 未处理的暂存消息全部发送到 失败队列
            if ((TIME_OUT * DateUtils.MILLIS_PER_DAY - this.cache.pttl(key, TimeUnit.MILLISECONDS)) > SEND_FAIL_TIME) {
                MessageAddress address = this.messageAddressService.getByAddress(key);
                // 被事务提交的地址 消息都需要发送
                if (address != null) {
                    this.cache.leftPush(this.getQueueKey(), this.cache.get(key));
                }
                // 删除 待发消息
                this.cache.del(key);
            }
        }
    }


    /**
     * 定时重试失败队列 半小时扫描一次
     */
    @Scheduled(cron = "0 0/30 * * * *")
    public void compensate() {
        this.logger.debug("消息失败补偿");
        List<MessageWrapper> list = new ArrayList<>();
        MessageWrapper message;
        while ((message = this.cache.rightPop(this.getQueueKey())) != null) {
            if (sendSimpleMessageRetry(message.getMessage())) {
                this.messageAddressService.delAddress(message.getKey());
            } else {
                // 未超出次数的重新放入队列
                if (message.getCounter() + 1 < RETRY_TIMES) {
                    message.setCounter(message.getCounter() + 1);
                    list.add(message);
                } else {
                    this.logger.error("队列 消息发送失败：{}", message);
                }
            }
        }
        // 未达到重试次数的 重新放回队列
        if (!CollectionUtils.isEmpty(list)) {
            this.cache.leftPushAll(this.getQueueKey(), list);
        }
    }


    /**
     * 直接发送
     *
     * @param message BaseMqMessage
     * @return
     */
    public abstract void sendSimpleMessage(BaseMqMessage message);

    /**
     * 直接发送
     *
     * @param mqMessage BaseMqMessage
     * @return
     */
    public abstract void sendOrderMessage(BaseMqMessage mqMessage);

    /**
     * 失败队列key
     */
    private String getQueueKey() {
        return SEND_FAIL_QUEUE + ":" + this.appName;
    }

    /**
     * 暂存key
     *
     * @param random 随机码
     * @return
     */
    private String getCacheKey(final String random) {
        return MESSAGE_CACHE_KEY_PREFIX + ":" + this.appName + ":" + random;
    }


}

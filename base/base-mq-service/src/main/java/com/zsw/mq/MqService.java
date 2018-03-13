package com.zsw.mq;

import ch.qos.logback.classic.Logger;
import com.zsw.base.utils.DateUtils;
import com.zsw.base.utils.SpringContextUtils;
import com.zsw.mq.cache.BaseMqMessageCache;
import com.zsw.mq.cache.impl.BaseMqMessageRedis;
import com.zsw.mq.persistence.bean.MessageAddress;
import com.zsw.mq.service.MessageAddressService;
import com.zsw.utils.RandomCodeUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * This is a test demo.
 *
 * @author ZhangShaowei on 2018/3/6 15:17
 **/
@Service
public class MqService {

    /**
     * LOGGER
     */
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

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
    private static final String SEND_FAIL_QUEUE = "mq:sendFail:queue";

    /**
     * 待发消息 prefix
     */
    private static final String MESSAGE_CACHE_KEY_PREFIX = "mq:cache:";

    /**
     * 消息暂存时间
     */
    private static final long TIME_OUT = 3;


    /**
     * 标记为发送失败的消息 - 代发表中 超过 30S未发送的 标记为失败
     * 程序启动时写入到失败队列
     */
    private static final long SEND_FAIL_TIME = 30 * 1000L;
    /**
     * 消息暂存时间单位
     */
    private static final TimeUnit TIME_UNIT = TimeUnit.DAYS;

    /**
     * 注入 不同类型的消息 发送方式
     */
    @Autowired
    private BaseMqService baseMqService;

    /**
     *
     */
    @Autowired
    private MessageAddressService messageService;

    /**
     * 定制的 消息缓存 带泛型
     */
    @Resource(name = BaseMqMessageRedis.BEAN_NAME)
    private BaseMqMessageCache cache;


    /**
     * 自定义事物消息，当前事物成功提交后发送
     *
     * @param message BaseMqMessage
     */
    public void transactionSimpleMessage(final BaseMqMessage message) {
        // step.1 暂存消息，分配一个随机的key,消息发送成功后删除缓存
        final String key = MESSAGE_CACHE_KEY_PREFIX + message.getTags() + ":" + RandomCodeUtils.randomCode();
        // 消息主体存redis
        this.cache.set(key, message, TIME_OUT, TIME_UNIT);
        // 地址存放数据库 - 回查消息若找不到此条消息 则为废弃消息 不予发送
        final MessageAddress address = this.messageService.add(key, message);

        // step.2 创建当前事务监听器 在当前事物提交后发送消息
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            /**
             * 事物成功提交后 发送
             *
             * 这是个阻塞方法 需异步
             */
            @Override
            public void afterCommit() {
                // 这里无法使用 AopContext.currentProxy() 获取当前代理对象
                // ThreadLocal中的对象是调用此 service 的上级对象
                SpringContextUtils.getBean("mqService", MqService.class)
                        .sendMessageAsync(message, address, key);
            }

            @Override
            public void afterCompletion(int status) {
                // 事务提交失败 尝试删除暂存消息
                if (STATUS_ROLLED_BACK == status) {
                    // 删除暂存
                    cache.del(key);
                }
            }
        });

    }

    /**
     * 异步发送消息
     * <p>
     * 需要配置 Executor {@link com.zsw.base.config.ThreadPoolConfiguration}
     *
     * @param message BaseMqMessage
     * @param address MessageAddress
     * @param key key
     */
    @Async
    public void sendMessageAsync(BaseMqMessage message, MessageAddress address, String key) {
        // 发送消息
        if (sendSimpleMessageRetry(message)) {
            // 成功后删除消息地址
            messageService.del(address);
        } else {
            // 失败后进入失败队列
            cache.rightPush(SEND_FAIL_QUEUE + ":" + message.getTags(), message);
        }
        cache.del(key);
    }

    /**
     * 重试
     *
     * @param message BaseMqMessage
     * @return true or false
     */
    public boolean sendSimpleMessageRetry(BaseMqMessage message) {
        int times = 0;
        while (times < RETRY_TIMES) {
            times++;
            try {
                this.baseMqService.sendSimpleMessage(message);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                this.logger.warn("消息发送失败 第 " + times + " 次 ：" + message);
            }
//            try {
//                Thread.sleep(RETRY_WAIT_TIME);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        return false;
    }


    /**
     * 暂存消息补偿
     */
    @PostConstruct
    public void sendFail() {
        this.logger.info("暂存消息补偿...");
        // 这是 所有暂存消息 key
        Set<String> keys = this.cache.keys(MESSAGE_CACHE_KEY_PREFIX + "*");
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        keys.forEach(key -> {
            if ((TIME_OUT * DateUtils.MILLIS_PER_DAY - this.cache.pttl(key, TimeUnit.MILLISECONDS)) > SEND_FAIL_TIME) {
                MessageAddress address = this.messageService.getByAddress(key);
                // 被事务提交的地址 消息都需要发送
                if (address != null) {
                    // 超过 30s 未处理的暂存消息全部发送到 失败队列
                    this.cache.rightPush(SEND_FAIL_QUEUE, this.cache.get(key));
                }
                // 删除 待发消息
                this.cache.del(key);
                // 删除 地址
                this.messageService.del(address);
            }
        });
    }

    /**
     * 每小时执行一次
     */
    @Scheduled(cron = "0 * * * * *")
    public void compensate() {
        this.logger.info("消息失败补偿...");
        BaseMqMessage message;
        while ((message = this.cache.leftPop(SEND_FAIL_QUEUE)) != null) {
            if (!sendSimpleMessageRetry(message)) {
                this.logger.error("消息发送失败： " + message.toString());
            }
//            MessageAddress address = this.messageAddressDao.getByAddress(???)
        }
    }

}

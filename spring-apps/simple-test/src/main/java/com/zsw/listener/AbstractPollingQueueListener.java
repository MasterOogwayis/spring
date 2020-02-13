package com.zsw.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * 轮训队列
 *
 * @author ZhangShaowei on 2020/2/11 15:43
 */
@Slf4j
@AllArgsConstructor
public abstract class AbstractPollingQueueListener<T extends Serializable>
        implements QueueMessageListener<T>, InitializingBean {

    /**
     * 处理的队列
     */
    public DefaultRedisList<T> queue;

    /**
     * 需要一个线程池
     */
    public Executor executor;

    /**
     * 多个线程处理
     */
    private Integer size = 1;


    /**
     * 启动一个线程轮训
     */
    @Override
    public void afterPropertiesSet() {
        for (int i = 0; i < size; i++) {
            this.executor.execute(new Worker());
        }
    }


    class Worker implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                T t = null;
                try {
                    // 这里不使用无阻塞时间的 take，为什么呢？因为连接有超时时间
                    // 限定轮训时间，到时候继续轮训就行
                    t = AbstractPollingQueueListener.this.queue.poll(10, TimeUnit.SECONDS);
                    if (Objects.nonNull(t)) {
                        AbstractPollingQueueListener.this.onMessage(t);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    log.error("消息轮训失败: queue = {}, message={}", queue, t, e);
                    if (Objects.nonNull(t)) {
                        AbstractPollingQueueListener.this.queue.offer(t);
                    }
                }
            }
        }
    }
}

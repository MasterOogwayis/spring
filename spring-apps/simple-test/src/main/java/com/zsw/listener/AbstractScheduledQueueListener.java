package com.zsw.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.Time;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 定时清空队列
 *
 * @author ZhangShaowei on 2020/2/13 14:03
 */
@Slf4j
@AllArgsConstructor
public abstract class AbstractScheduledQueueListener<T extends Serializable> implements QueueMessageListener<T> {

    public DefaultRedisList<T> queue;

    /**
     * 延迟 10s 执行，每次执行完以后间隔 30s 再次执行
     */
    @Scheduled(initialDelay = 2 * 1000L, fixedRate = 60 * 1000L)
    public void poll() {
        T t;
        List<T> onError = new ArrayList<>();
        int counter = 0;
        long timer = System.currentTimeMillis();
        while ((t = this.queue.poll()) != null) {
            try {
                this.onMessage(t);
                counter ++;
            } catch (Exception e) {
                log.error("Scheduled message fail: {}, message={}", this, t);
                onError.add(t);
            }
        }
        if (!CollectionUtils.isEmpty(onError)) {
            this.queue.addAll(0, onError);
        }
        log.debug("Scheduled queue end {}... success={}, fail={}, cost={}ms", this, counter, onError.size(), System.currentTimeMillis() - timer);
    }


}

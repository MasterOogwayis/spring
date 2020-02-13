package com.zsw.queue;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.support.collections.DefaultRedisList;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 入队 {@link DefaultRedisList#offer(Object)}
 * 出队 {@link DefaultRedisList#take()} 注意，通常连接有超时时间。所以一般使用  {@link DefaultRedisList#poll(long, TimeUnit)}}
 *
 * @author ZhangShaowei on 2020/2/13 14:10
 */
public class RedisQueue<T extends Serializable> extends DefaultRedisList<T> {
    public RedisQueue(String key, RedisOperations<String, T> operations) {
        super(key, operations);
    }
}

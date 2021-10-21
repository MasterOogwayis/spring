package com.zsw.redis;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.support.collections.DefaultRedisList;

/**
 * @author ZhangShaowei on 2020/2/19 14:38
 */
public class SmsQueue extends DefaultRedisList<Object> {
    public SmsQueue(String key, RedisOperations<String, Object> operations) {
        super(key, operations);
    }
}

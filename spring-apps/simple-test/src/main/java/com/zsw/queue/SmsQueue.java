package com.zsw.queue;

import com.zsw.pojo.Sms;
import org.springframework.data.redis.core.RedisOperations;

/**
 * @author ZhangShaowei on 2020/2/11 15:34
 */
public class SmsQueue extends RedisQueue<Sms> {
    public SmsQueue(String key, RedisOperations<String, Sms> operations) {
        super(key, operations);
    }
}

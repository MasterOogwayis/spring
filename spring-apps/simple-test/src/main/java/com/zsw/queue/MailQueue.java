package com.zsw.queue;

import com.zsw.pojo.Mail;
import org.springframework.data.redis.core.RedisOperations;

/**
 * @author ZhangShaowei on 2020/2/11 15:34
 */
public class MailQueue extends RedisQueue<Mail> {
    public MailQueue(String key, RedisOperations<String, Mail> operations) {
        super(key, operations);
    }
}

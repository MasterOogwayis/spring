package com.zsw;

import com.zsw.listener.MailListener;
import com.zsw.listener.SmsListener;
import com.zsw.pojo.Mail;
import com.zsw.pojo.Sms;
import com.zsw.queue.MailQueue;
import com.zsw.queue.SmsQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ZhangShaowei on 2020/2/11 14:11
 */
@Configuration
@EnableScheduling
public class MessageQueueConfiguration {

//    @Bean
//    public MailListener mailListener(MailQueue queue) {
//        return new MailListener(queue);
//    }
//
//    @Bean
//    public SmsListener smsListener(SmsQueue queue, ThreadPoolTaskExecutor executorService) {
//        return new SmsListener(queue, executorService, 2);
//    }

    @Bean
    public MailQueue mailQueue(RedisTemplate<String, Mail> redisTemplate) {
        return new MailQueue("queue:mail", redisTemplate);
    }

    @Bean
    public SmsQueue smsQueue(RedisTemplate<String, Sms> redisTemplate) {
        return new SmsQueue("queue:sms", redisTemplate);
    }


}

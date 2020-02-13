package com.zsw.listener;

import com.zsw.pojo.Sms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ZhangShaowei on 2020/2/11 15:43
 */
@Slf4j
public class SmsListener extends AbstractPollingQueueListener<Sms> {

    public SmsListener(DefaultRedisList<Sms> queue, ThreadPoolTaskExecutor executorService, Integer size) {
        super(queue, executorService, size);
    }

    @Override
    public void onMessage(Sms sms) {
        log.info("take Sms from queue: {}", sms);
    }
}

package com.zsw.listener;

import com.zsw.pojo.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.support.collections.DefaultRedisList;

/**
 * @author ZhangShaowei on 2020/2/11 15:43
 */
@Slf4j
public class MailListener extends AbstractScheduledQueueListener<Mail> {


    public MailListener(DefaultRedisList<Mail> queue) {
        super(queue);
    }

    @Override
    public void onMessage(Mail mail) {
        log.info("received mail from queue: {}", mail);
    }
}

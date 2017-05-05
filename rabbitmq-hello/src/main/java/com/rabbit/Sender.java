package com.rabbit;

import org.apache.http.client.utils.DateUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ZhangShaowei on 2017/5/3 13:33
 */
@Component
public class Sender {

    /**
     *
     */
    @Autowired
    private AmqpTemplate amqpTemplate;


    /**
     *
     */
    public void send() {
        String context = "Sender : hello " + DateUtils.formatDate(new Date());
        System.out.println(context);
        this.amqpTemplate.convertAndSend("hello", context);
    }


}

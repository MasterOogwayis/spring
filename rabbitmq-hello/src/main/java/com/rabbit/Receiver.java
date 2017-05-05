package com.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2017/5/3 13:35
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    /**
     * @param hello
     */
    @RabbitHandler
    public void process(final String hello){
        System.out.println("Receiver : " + hello);
    }

}

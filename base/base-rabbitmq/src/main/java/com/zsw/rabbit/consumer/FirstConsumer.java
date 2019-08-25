package com.zsw.rabbit.consumer;

import com.zsw.rabbit.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author Administrator on 2019/8/25 19:24
 **/
@Slf4j
@Component
@RabbitListener(queues = "${com.zsw.rabbitmq.queue.first}", containerFactory = "simpleRabbitListenerContainerFactory")
public class FirstConsumer {

    @RabbitHandler
    public void handler(@Payload Product product) {
        log.info("consumer 1, receive message: {}", product);
    }

}

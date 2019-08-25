package com.zsw.rabbit.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Administrator on 2019/8/25 19:24
 **/
@Slf4j
@Component
@RabbitListener(queues = "${com.zsw.rabbitmq.queue.second}", containerFactory = "simpleRabbitListenerContainerFactory")
public class SecondConsumer {

    @SneakyThrows
    @RabbitHandler
    public void handler(String content, Channel channel, Message message) {
        log.info("consumer 2, receive message: {}", content);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}

package com.zsw.demo.rabbitmq.javaapi.delay;

import com.rabbitmq.client.*;
import com.zsw.demo.rabbitmq.utils.RabbitMQUtils;
import com.zsw.rabbit.config.RabbitType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 延迟队列
 *
 * @author ZhangShaowei on 2019/8/26 10:31
 **/
@Slf4j
public class DelayedConsumer {

    public static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String DELAYED_EXCHANGE_NAME = "DELAYED_EXCHANGE";
    public static final String DELAYED_QUEUE_NAME = "DELAYED_QUEUE";


    @SneakyThrows
    public static void main(String[] args) {
        ConnectionFactory factory = RabbitMQUtils.create();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //申明交换机
        channel.exchangeDeclare(
                DELAYED_EXCHANGE_NAME,
                RabbitType.DELAYED.getValue(),
                false,
                false,
                Collections.singletonMap("x-delayed-type", RabbitType.DIRECT.getValue())
        );

        // 申明队列
        channel.queueDeclare(DELAYED_QUEUE_NAME, false, false, false, null);

        //绑定队列交换机
        channel.queueBind(DELAYED_QUEUE_NAME, DELAYED_EXCHANGE_NAME, "zsw.delayed");


        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, UTF_8);
                log.info("message={}, 消费时间={}", message, LocalDateTime.now().format(TIMESTAMP_FORMAT));
            }
        };

        channel.basicConsume(DELAYED_QUEUE_NAME, true, consumer);

    }


}

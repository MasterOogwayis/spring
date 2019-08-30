package com.zsw.demo.rabbitmq.javaapi.simple;

import com.rabbitmq.client.*;
import com.zsw.demo.rabbitmq.utils.RabbitMQUtils;
import com.zsw.rabbit.config.RabbitType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ZhangShaowei on 2019/8/26 10:31
 **/
@Slf4j
public class SimpleConsumer {

    public static final String EXCHANGE_NAME = "SIMPLE_EXCHANGE";
    public static final String QUEUE_NAME = "SIMPLE_QUEUE";


    @SneakyThrows
    public static void main(String[] args) {
        ConnectionFactory factory = RabbitMQUtils.create();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //申明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, RabbitType.DIRECT.getValue(), false, false, null);

        // 申明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "zsw.test");


        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, UTF_8);
                log.info("message={}, consumerTag={}, deliveryTag={}", message, consumerTag, envelope.getDeliveryTag());
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);

    }


}

package com.zsw.lesson.rabbitmq.javaapi.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zsw.lesson.rabbitmq.utils.RabbitMQUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

import static com.zsw.lesson.rabbitmq.config.RabbitMQConstant.CONFIRM_QUEUE;

/**
 * 普通确认方式
 *
 * @author ZhangShaowei on 2019/8/27 13:22
 **/
@Slf4j
public class NormalConfirmProducer {


    @SneakyThrows
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = RabbitMQUtils.create();
        @Cleanup Connection connection = connectionFactory.newConnection();

        @Cleanup Channel channel = connection.createChannel();

        channel.queueDeclare(CONFIRM_QUEUE, false, false, false, null);

        // 声明队列（默认交换机AMQP default，Direct）
        String message = "RabbitMQ, Normal Confirm.";

        // 开启发送方确认模式
        channel.confirmSelect();

        channel.basicPublish("", CONFIRM_QUEUE, null, message.getBytes(Charset.forName("utf-8")));

        if (channel.waitForConfirms()) {
            log.info("消息发送成功");
        }

    }

}

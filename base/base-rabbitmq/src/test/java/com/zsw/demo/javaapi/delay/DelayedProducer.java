package com.zsw.demo.javaapi.delay;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zsw.demo.utils.RabbitMQUtils;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static com.zsw.demo.javaapi.delay.DelayedConsumer.DELAYED_EXCHANGE_NAME;
import static com.zsw.demo.javaapi.delay.DelayedConsumer.TIMESTAMP_FORMAT;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ZhangShaowei on 2019/8/26 10:31
 **/
public class DelayedProducer {

    @SneakyThrows
    public static void main(String[] args) {
        ConnectionFactory factory = RabbitMQUtils.create();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 发送时间
        LocalDateTime sendTime = LocalDateTime.now();
        // 10s 后投递
        LocalDateTime localDateTime = sendTime.plusSeconds(10);

        String message = "发送时间：" + sendTime.format(TIMESTAMP_FORMAT) + "，投递时间：" + localDateTime.format(TIMESTAMP_FORMAT);

        AMQP.BasicProperties.Builder headers = new AMQP.BasicProperties().builder().headers(Collections.singletonMap("x-delay", TimeUnit.SECONDS.toMillis(10)));

        channel.basicPublish(DELAYED_EXCHANGE_NAME, "zsw.delayed", headers.build(), message.getBytes(UTF_8));

        channel.close();
        connection.close();

    }

}

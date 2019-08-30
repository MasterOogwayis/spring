package com.zsw.demo.rabbitmq.javaapi.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zsw.demo.rabbitmq.utils.RabbitMQUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

import static com.zsw.demo.rabbitmq.config.RabbitMQConstant.CONFIRM_QUEUE;

/**
 * 批量确认方式
 *
 * @author ZhangShaowei on 2019/8/27 13:22
 **/
@Slf4j
public class BatchConfirmProducer {


    @SneakyThrows
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = RabbitMQUtils.create();
        @Cleanup Connection connection = connectionFactory.newConnection();

        @Cleanup Channel channel = connection.createChannel();

        channel.queueDeclare(CONFIRM_QUEUE, false, false, false, null);

        // 声明队列（默认交换机AMQP default，Direct）
        String message = "RabbitMQ, Batch Confirm.";

        // 开启发送方确认模式
        channel.confirmSelect();

        for (int i = 0; i < 5; i++) {
            channel.basicPublish("", CONFIRM_QUEUE, null, (message + "_" + i).getBytes(Charset.forName("utf-8")));
        }

        // 批量确认结果，ACK如果是Multiple=True，代表ACK里面的Delivery-Tag之前的消息都被确认了
        // 比如5条消息可能只收到1个ACK，也可能收到2个（抓包才看得到）
        // 直到所有信息都发布，只要有一个未被Broker确认就会IOException
        channel.waitForConfirmsOrDie();

        log.info("消息发送完毕，批量确认成功...");

        if (channel.waitForConfirms()) {
            log.info("消息发送成功");
        }

    }

}

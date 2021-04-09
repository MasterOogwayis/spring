package com.zsw.lesson.rabbitmq.javaapi.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zsw.lesson.rabbitmq.utils.RabbitMQUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import static com.zsw.lesson.rabbitmq.config.RabbitMQConstant.CONFIRM_QUEUE;

/**
 * 异步确认
 *
 * @author ZhangShaowei on 2019/8/26 10:31
 **/
@Slf4j
public class AsyncConfirmProducer {

    @SneakyThrows
    public static void main(String[] args) {
        ConnectionFactory factory = RabbitMQUtils.create();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(CONFIRM_QUEUE, false, false, false, null);

        // 存放未确认消息的 deliveryTag
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());

        String message = "Async Confirm, Rabbit MQ.";

        // 这里不会打印所有响应的ACK；ACK可能有多个，有可能一次确认多条，也有可能一次确认一条
        // 异步监听确认和未确认的消息
        // 如果要重复运行，先停掉之前的生产者，清空队列
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                log.info("Broker 已确认消息，标示：{}，多个消息：{}", deliveryTag, multiple);
                if (multiple) {
                    // headSet表示后面参数之前的所有元素，全部删除
                    confirmSet.headSet(deliveryTag + 1L).clear();
                } else {
                    // 只移除一个元素
                    confirmSet.remove(deliveryTag);
                }
                log.warn("未确认消息：{}", confirmSet);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                log.warn("Broker 未确认消息，标示：{}", deliveryTag);
                if (multiple) {
                    // headSet 表示后面参数之前的所有元素，全部删除
                    confirmSet.headSet(deliveryTag + 1L).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
                // 可以在这里重发
            }
        });

        // 开启发送确认方式
        channel.confirmSelect();
        for (int i = 0; i < 10; i++) {
            long netSeqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", CONFIRM_QUEUE, null, (message + "_" + i).getBytes(Charset.forName("utf-8")));
            confirmSet.add(netSeqNo);
        }
        log.info("所有消息：{}", confirmSet);

        // 不能立即关闭channel，可能收不到后面的ACK
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();

    }

}

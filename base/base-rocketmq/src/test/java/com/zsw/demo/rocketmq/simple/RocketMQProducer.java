package com.zsw.demo.rocketmq.simple;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.zsw.demo.rocketmq.RocketMQProperties.*;

/**
 * @author ZhangShaowei on 2019/8/30 13:23
 **/
@Slf4j
public class RocketMQProducer {


    @SneakyThrows
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer(SIMPLE_GROUP);
        producer.setNamesrvAddr(NAME_SERVER_ADDR);

        producer.start();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String line;
            while (!"shutdown".equalsIgnoreCase((line = reader.readLine()))) {
                if (!StringUtils.isNotEmpty(line)) {
                    continue;
                }
                Message message = new Message(TOPIC, "tagA", line.getBytes(UTF8));
                SendResult sendResult = producer.send(message);
                log.info("result: {}", sendResult);
            }
        } finally {
            producer.shutdown();
        }
    }

}

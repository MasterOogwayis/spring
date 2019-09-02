package com.zsw.demo.rocketmq.simple;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.zsw.demo.rocketmq.RocketMQProperties.*;

/**
 * 发送事务消息
 *
 * @author ZhangShaowei on 2019/8/30 13:23
 **/
@Slf4j
public class RocketMQTransactionProducer {


    @SneakyThrows
    public static void main(String[] args) {
        send();
    }



    public static void send() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(SIMPLE_GROUP);
        producer.setNamesrvAddr(NAME_SERVER_ADDR);
        producer.setInstanceName("Producer");
        producer.setClientIP("192.168.137.1");
        producer.setRetryTimesWhenSendFailed(3);

        producer.start();
        log.info("Producer started ...");
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String line;
            while (!"shutdown".equalsIgnoreCase((line = reader.readLine()))) {
                if (!StringUtils.isNotEmpty(line)) {
                    continue;
                }
                Message message = new Message(TOPIC, "tagA", line.getBytes(UTF8));
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("result: {}", sendResult);
                    }

                    @Override
                    public void onException(Throwable e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                });
            }
        } finally {
            producer.shutdown();
        }
    }

}

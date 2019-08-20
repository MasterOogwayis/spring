package com.zsw.kafka.demo;

import com.zsw.kafka.utils.ExecutorUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author ZhangShaowei on 2019/8/20 13:06
 **/
@Slf4j
public class BaseConsumer implements Runnable {


    private String topic;

    private KafkaConsumer<Integer, String> consumer;

    public BaseConsumer(String topic) {
        this.topic = topic;
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.137.110:9092,192.168.137.111:9092,192.168.137.112:9092");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "zsw-gid");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, "zsw");
        properties.setProperty(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<>(properties);
        this.consumer.subscribe(Collections.singleton(this.topic));
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<Integer, String> poll = this.consumer.poll(Duration.ofMillis(100));
            poll.forEach(record -> {
                log.info("key={}, value={}, offset={}, partition={}", record.key(), record.value(), record.offset(), record.partition());
            });
        }
    }


    @SneakyThrows
    public static void main(String[] args) {
        ExecutorUtil.execute(new BaseConsumer("test"));
        ExecutorUtil.execute(new BaseConsumer("test"));

        System.in.read();
    }

}

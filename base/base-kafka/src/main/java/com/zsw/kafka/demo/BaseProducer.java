package com.zsw.kafka.demo;

import com.zsw.kafka.support.CustomPartitioner;
import com.zsw.kafka.utils.ExecutorUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/8/20 13:06
 **/
@Slf4j
public class BaseProducer implements Runnable {

    private String topic;

    private KafkaProducer<Integer, String> producer;

    public BaseProducer(String topic) {
        this.topic = topic;
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.137.110:9092,192.168.137.111:9092,192.168.137.112:9092");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
        this.producer = new KafkaProducer<>(properties);
    }

    @Override
    @SneakyThrows
    public void run() {
        int i = 0;
        while (true) {
            this.producer.send(new ProducerRecord<>(this.topic, i++, "hello " + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    log.info("topic={}, offset={}, partition={}", recordMetadata.topic(), recordMetadata.offset(), recordMetadata.partition());
                }
            });

            TimeUnit.SECONDS.sleep(1);
        }
    }


    @SneakyThrows
    public static void main(String[] args) {
        ExecutorUtil.execute(new BaseProducer("test"));
        System.in.read();
    }

}

package com.zsw.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static com.zsw.kafka.KafkaProperties.BROKER_LIST;
import static com.zsw.kafka.KafkaProperties.TOPIC;

/**
 * @author ZhangShaowei on 2020/6/23 15:03
 */
@Slf4j
public class ProducerTests {



    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


        ProducerRecord<String, String> message = new ProducerRecord<>(TOPIC, "Hello, this is Mr Zhang speaking.");

        producer.send(message);
        producer.send(message, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {

            }
        });

        producer.close();

    }


}

package com.zsw.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2019/8/20 13:50
 **/
@Slf4j
@Service
public class ConsumerService {

    @KafkaListener(topics = {"test"}, groupId = "zsw-gid")
    public void listener(ConsumerRecord<Integer, String> record) {
//        records.forEach(record -> {
        log.info("listener: key={}, value={}, offset={}, partition={}", record.key(), record.value(), record.offset(), record.partition());
//        });
    }

//    @KafkaListener(topics = {"test"}, groupId = "zsw-gid")
//    public void listener2(ConsumerRecord<Integer, String> record) {
////        records.forEach(record -> {
//        log.info("listener2: key={}, value={}, offset={}, partition={}", record.key(), record.value(), record.offset(), record.partition());
////        });
//    }


}

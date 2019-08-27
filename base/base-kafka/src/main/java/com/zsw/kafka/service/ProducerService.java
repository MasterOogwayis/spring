package com.zsw.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author ZhangShaowei on 2019/8/20 13:59
 **/
@Slf4j
@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<Integer, String> producer;


    public void send(String topic, Integer key, String value) {
        ListenableFuture<SendResult<Integer, String>> send = this.producer.send(topic, key, value);
        send.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(SendResult<Integer, String> sendResult) {
                log.info("send: partition={}", sendResult.getRecordMetadata().partition());
            }
        });
    }

}

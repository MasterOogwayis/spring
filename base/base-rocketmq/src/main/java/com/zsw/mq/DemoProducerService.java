package com.zsw.mq;

import com.zsw.mq.spring.api.AsyncCallback;
import com.zsw.mq.spring.api.SendResult;
import com.zsw.mq.spring.core.RocketMQTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author ZhangShaowei on 2019/9/12 16:29
 **/
@Slf4j
//@Service
public class DemoProducerService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public void test() {
        // normal
        // 1. sendOneway
        this.rocketMQTemplate.sendOneway("topic", "tags", "data");
        // 2. sendSync
        SendResult sendResult = this.rocketMQTemplate.sendSync("topic", "tags", "data");
        log.info("success: {}", sendResult);
        // 2. sendAsync
        this.rocketMQTemplate.sendAsync("topic", "tags", "data", new AsyncCallback() {
            @Override
            public void success(SendResult sendResult) {
                log.info("success: {}", sendResult);
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("send faild: {}", throwable.getMessage());
            }
        });


        // spring message
        // 1. sendOneway
        this.rocketMQTemplate.send("topicName:tags", MessageBuilder.withPayload("data").build());
        // 2. sendSync
        SendResult data = this.rocketMQTemplate.syncSend("topicName:tags", MessageBuilder.withPayload("data").build());
        log.info("success: {}", data);
        // 3. sendAsync
        this.rocketMQTemplate.asyncSend("topic", "data", new AsyncCallback() {
            @Override
            public void success(SendResult sendResult) {
                log.info("success: {}", sendResult);
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("send faild: {}", throwable.getMessage());
            }
        });
        this.rocketMQTemplate.asyncSend("topic", MessageBuilder.withPayload("data").build(), new AsyncCallback() {
            @Override
            public void success(SendResult sendResult) {
                log.info("success: {}", sendResult);
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("send faild: {}", throwable.getMessage());
            }
        });
    }

}

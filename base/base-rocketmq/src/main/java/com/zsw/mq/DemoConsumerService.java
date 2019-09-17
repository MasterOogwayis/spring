package com.zsw.mq;

import com.zsw.mq.spring.core.RocketMQListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhangShaowei on 2019/9/6 10:06
 **/
@Slf4j
//@RocketMQMessageListener(
//        topic = "topic",
//        consumerGroup = "group",
//        messageModel = MessageModel.CLUSTERING,
//        accessKey = "XXX",
//        secretKey = "XXX"
//)
public class DemoConsumerService implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("thread={}, receive message : {}", Thread.currentThread().getName(), message);
        throw new RuntimeException("不消费");
    }
}

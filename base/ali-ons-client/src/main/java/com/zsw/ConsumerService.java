package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2019/9/6 10:06
 **/
@Slf4j
@Service
@RocketMQMessageListener(
        topic = "BCPT_SC_TEST",
        consumerGroup = "GID_BCPT_SC_TEST",
        messageModel = MessageModel.CLUSTERING,
        accessKey = "LTAIxhbAKjb2ziY2",
        secretKey = "Ybotwl4bPGWAF3PwN3EbnpfPYS6AqW"
)
public class ConsumerService implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("thread={}, receive message : {}", Thread.currentThread().getName(), message);
    }
}

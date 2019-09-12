package com.zsw.mq;

import com.zsw.mq.spring.annotation.MessageModel;
import com.zsw.mq.spring.annotation.RocketMQMessageListener;
import com.zsw.mq.spring.core.RocketMQListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2019/9/6 10:06
 **/
@Slf4j
@Service
@RocketMQMessageListener(
        topic = "BCPT_CRM_TEST",
        consumerGroup = "GID_BCPT_CRM_TEST",
        messageModel = MessageModel.CLUSTERING,
        accessKey = "LTAIxhbAKjb2ziY2",
        secretKey = "Ybotwl4bPGWAF3PwN3EbnpfPYS6AqW"
)
public class ConsumerService implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("thread={}, receive message : {}", Thread.currentThread().getName(), message);
        throw new RuntimeException("不消费");
    }
}

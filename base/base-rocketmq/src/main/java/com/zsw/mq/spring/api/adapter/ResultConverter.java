package com.zsw.mq.spring.api.adapter;

import com.zsw.mq.spring.api.SendResult;
import org.springframework.core.convert.converter.Converter;

/**
 * @author ZhangShaowei on 2019/9/12 11:27
 **/
class ResultConverter implements Converter<org.apache.rocketmq.client.producer.SendResult, SendResult> {
    @Override
    public SendResult convert(org.apache.rocketmq.client.producer.SendResult sendResult) {
        return DefaultSendResult.builder().messageId(sendResult.getMsgId()).topic(sendResult.getMessageQueue().getTopic()).build();
    }
}

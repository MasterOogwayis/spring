package com.zsw.mq.spring.adapter.convert;

import com.zsw.mq.spring.adapter.SendResult;
import com.zsw.mq.spring.adapter.core.AliSendResult;
import org.springframework.core.convert.converter.Converter;

/**
 * @author ZhangShaowei on 2019/9/12 11:27
 **/
public class AliyunResultConverter implements Converter<com.aliyun.openservices.ons.api.SendResult, SendResult> {
    @Override
    public SendResult convert(com.aliyun.openservices.ons.api.SendResult sendResult) {
        return AliSendResult.builder().topic(sendResult.getTopic()).messageId(sendResult.getMessageId()).build();
    }
}

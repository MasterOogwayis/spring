package com.anze.mq.spring.api.impl.ali;

import com.anze.mq.spring.api.DataConvertor;
import com.anze.mq.spring.api.SendResult;

/**
 * @author ZhangShaowei on 2019/9/11 16:52
 **/
public class AliDataConvertor implements DataConvertor<com.aliyun.openservices.ons.api.SendResult, SendResult> {

    @Override
    public SendResult convert(com.aliyun.openservices.ons.api.SendResult sendResult){
        AliSendResult result = new AliSendResult();
        result.setMessageId(sendResult.getMessageId());
        result.setTopic(sendResult.getTopic());
        return result;
    }

}

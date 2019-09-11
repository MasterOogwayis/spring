package com.anze.mq.spring.api.impl.def;

import com.anze.mq.spring.api.SendResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangShaowei on 2019/9/11 16:24
 **/
@Setter
@Getter(onMethod = @_({@Override}))
@AllArgsConstructor
public class DefaultSendResult extends org.apache.rocketmq.client.producer.SendResult implements SendResult {
    private static final long serialVersionUID = -5540608359798883871L;

    @Override
    public String getTopic() {
        return super.getMessageQueue().getTopic();
    }

    @Override
    public String getMessageId() {
        return super.getMsgId();
    }
}

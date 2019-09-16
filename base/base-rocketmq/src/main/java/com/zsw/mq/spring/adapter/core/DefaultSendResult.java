package com.zsw.mq.spring.adapter.core;

import com.zsw.mq.spring.adapter.SendResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ZhangShaowei on 2019/9/11 16:24
 **/
@ToString
@Setter
@Getter(onMethod = @_({@Override}))
@Builder
public class DefaultSendResult implements SendResult {
    private static final long serialVersionUID = -5540608359798883871L;

    private String topic;

    private String messageId;

}

package com.zsw.mq.spring.api.impl.def;

import com.zsw.mq.spring.api.SendResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangShaowei on 2019/9/11 16:24
 **/
@Setter
@Getter(onMethod = @_({@Override}))
@Builder
public class DefaultSendResult implements SendResult {
    private static final long serialVersionUID = -5540608359798883871L;

    private String topic;

    private String messageId;

}
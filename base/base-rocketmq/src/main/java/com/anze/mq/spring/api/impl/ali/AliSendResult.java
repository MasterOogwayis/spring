package com.anze.mq.spring.api.impl.ali;

import com.anze.mq.spring.api.SendResult;
import lombok.Builder;
import lombok.Getter;

/**
 * @author ZhangShaowei on 2019/9/11 16:29
 **/
@Getter(onMethod = @_({@Override}))
@Builder
public class AliSendResult implements SendResult {
    private static final long serialVersionUID = 3943022399645977324L;

    private String topic;

    private String messageId;

}

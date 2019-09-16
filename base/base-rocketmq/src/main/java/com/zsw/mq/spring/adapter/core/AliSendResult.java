package com.zsw.mq.spring.adapter.core;

import com.zsw.mq.spring.adapter.SendResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ZhangShaowei on 2019/9/11 16:29
 **/
@ToString
@Setter
@Getter(onMethod = @_({@Override}))
@Builder
public class AliSendResult implements SendResult {
    private static final long serialVersionUID = 3943022399645977324L;

    private String topic;

    private String messageId;

}

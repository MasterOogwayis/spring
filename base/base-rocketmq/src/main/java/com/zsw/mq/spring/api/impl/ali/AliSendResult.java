package com.zsw.mq.spring.api.impl.ali;

import com.zsw.mq.spring.api.SendResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangShaowei on 2019/9/11 16:29
 **/
@Setter
@Getter(onMethod = @_({@Override}))
@Builder
public class AliSendResult implements SendResult {
    private static final long serialVersionUID = 3943022399645977324L;

    private String topic;

    private String messageId;

}

package com.zsw.mq.spring.api.impl.ali;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

/**
 * @author ZhangShaowei on 2019/9/12 12:51
 **/
public class AliMessageListener implements MessageListener {
    @Override
    public Action consume(Message message, ConsumeContext context) {
        return null;
    }
}

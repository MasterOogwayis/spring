package com.zsw.mq.spring.api.adaptor;

import com.zsw.mq.spring.api.Consumer;
import com.zsw.mq.spring.api.MessageListener;
import com.zsw.mq.spring.autoconfigure.MessageSerializer;

/**
 * @author ZhangShaowei on 2019/9/12 13:12
 **/
public class AliConsumerAdaptor implements Consumer {

    private com.aliyun.openservices.ons.api.Consumer consumer;

    private MessageSerializer serializer;

    @Override
    public void start() {
        consumer.start();
    }

    @Override
    public void stop() {
        consumer.shutdown();
    }

    @Override
    public boolean isRunning() {
        return consumer.isStarted();
    }

    @Override
    public void subscribe(String topic, String subExpression, MessageListener listener) {

    }

    @Override
    public void subscribe(String topic, MessageListener listener) {

    }

    @Override
    public void unsubscribe(String topic) {

    }
}

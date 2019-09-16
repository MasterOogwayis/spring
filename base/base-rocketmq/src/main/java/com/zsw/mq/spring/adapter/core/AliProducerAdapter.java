package com.zsw.mq.spring.adapter.core;

import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.zsw.mq.spring.adapter.AsyncCallback;
import com.zsw.mq.spring.adapter.Producer;
import com.zsw.mq.spring.adapter.SendResult;
import com.zsw.mq.spring.adapter.convert.AliyunResultConverter;
import com.zsw.mq.spring.serializer.MessageSerializer;
import com.zsw.mq.spring.support.RocketMQHeaders;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Properties;

/**
 * @author ZhangShaowei on 2019/9/11 16:44
 **/
@Slf4j
public class AliProducerAdapter implements Producer {

    private com.aliyun.openservices.ons.api.bean.ProducerBean producer;

    private MessageSerializer serializer;

    private Converter<com.aliyun.openservices.ons.api.SendResult, SendResult> converter;

    public AliProducerAdapter(ProducerBean producer, MessageSerializer serializer) {
        this.producer = producer;
        this.serializer = serializer;
        converter = new AliyunResultConverter();
    }

    @Override
    public void sendOneway(String topic, String tags, Object data) {
        this.producer.sendOneway(this.create(topic, tags, "", data));
    }

    @Override
    public SendResult sendSync(String topic, String tags, Object data) {
        return this.converter.convert(this.producer.send(this.create(topic, tags, "", data)));
    }

    @Override
    public void sendAsync(String topic, String tags, Object data, AsyncCallback callback) {
        this.producer.sendAsync(this.create(topic, tags, "", data), new AliCallBack(callback));
    }

    @Override
    public void sendOneway(String topic, String tags, String keys, Object data) {
        this.producer.sendOneway(this.create(topic, tags, keys, data));
    }

    @Override
    public SendResult sendSync(String topic, String tags, String keys, Object data) {
        return this.converter.convert(this.producer.send(this.create(topic, tags, keys, data)));
    }

    @Override
    public void sendAsync(String topic, String tags, String keys, Object data, AsyncCallback callback) {
        this.producer.sendAsync(this.create(topic, tags, keys, data), new AliCallBack(callback));
    }

    @Override
    public SendResult syncSend(String destination, Message<?> message) {
        return this.converter.convert(this.producer.send(this.convertToRocketMessage(destination, message)));
    }

    @Override
    public void asyncSend(String destination, Message<?> message, AsyncCallback callback) {
        this.producer.sendAsync(this.convertToRocketMessage(destination, message), new AliCallBack(callback));
    }


    @Override
    public void start() {
        this.producer.start();
    }

    @Override
    public void stop() {
        this.producer.shutdown();
    }

    @Override
    public boolean isRunning() {
        return this.producer.isStarted();
    }


    @AllArgsConstructor
    class AliCallBack implements SendCallback {

        private AsyncCallback callback;

        @Override
        public void onSuccess(com.aliyun.openservices.ons.api.SendResult sendResult) {
            this.callback.success(AliProducerAdapter.this.converter.convert(sendResult));
        }

        @Override
        public void onException(OnExceptionContext context) {
            this.callback.onError(context.getException());
        }
    }

    private com.aliyun.openservices.ons.api.Message create(
            String topic,
            String tags,
            String key,
            Object data) {
        com.aliyun.openservices.ons.api.Message message = new com.aliyun.openservices.ons.api.Message();
        message.setTopic(topic);
        message.setTag(tags);
        message.setBody(this.serializer.serialize(data));
        if (StringUtils.hasText(key)) {
            message.setKey(key);
        }
        return message;
    }

    /**
     * @param destination topic:tags
     * @param message Spring Message
     * @return
     */
    public com.aliyun.openservices.ons.api.Message convertToRocketMessage(
            String destination,
            org.springframework.messaging.Message<?> message) {

        byte[] payloads = this.serializer.serialize(message.getPayload());

        String[] tempArr = destination.split(":", 2);
        String topic = tempArr[0];
        String tags = "";
        if (tempArr.length > 1) {
            tags = tempArr[1];
        }

        com.aliyun.openservices.ons.api.Message rocketMsg = new com.aliyun.openservices.ons.api.Message(topic, tags, payloads);

        MessageHeaders headers = message.getHeaders();

        if (!CollectionUtils.isEmpty(headers)) {
            Object keys = headers.get(RocketMQHeaders.KEYS);
            // if headers has 'KEYS', set rocketMQ message key
            if (!StringUtils.isEmpty(keys)) {
                rocketMsg.setKey(keys.toString());
            }
            Properties properties = new Properties();
            properties.putAll(headers);
            rocketMsg.setUserProperties(properties);
        }

        return rocketMsg;
    }


}

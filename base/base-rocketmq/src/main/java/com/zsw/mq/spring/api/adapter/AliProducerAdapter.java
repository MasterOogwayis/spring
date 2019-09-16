package com.zsw.mq.spring.api.adapter;

import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.zsw.mq.spring.api.AsyncCallback;
import com.zsw.mq.spring.api.Producer;
import com.zsw.mq.spring.api.SendResult;
import com.zsw.mq.spring.serializer.MessageSerializer;
import com.zsw.mq.spring.support.AliRocketMQUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

/**
 * @author ZhangShaowei on 2019/9/11 16:44
 **/
@Slf4j
@AllArgsConstructor
public class AliProducerAdapter implements Producer {

    private com.aliyun.openservices.ons.api.bean.ProducerBean producer;

    private MessageSerializer serializer;

    @Override
    public void sendOneway(String topic, String expression, Object data) {
        this.producer.sendOneway(AliRocketMQUtil.convert(topic, expression, "", data, serializer));
    }

    @Override
    public SendResult sendSync(String topic, String expression, Object data) {
        return AliRocketMQUtil.tranResult(this.producer.send(AliRocketMQUtil.convert(topic, expression, "", data, serializer)));
    }

    @Override
    public void sendAsync(String topic, String expression, Object data, AsyncCallback callback) {
        this.producer.sendAsync(AliRocketMQUtil.convert(topic, expression, "", data, serializer), new AliCallBack(callback));
    }

    @Override
    public void sendOneway(String topic, String expression, String keys, Object data) {
        this.producer.sendOneway(AliRocketMQUtil.convert(topic, expression, keys, data, serializer));
    }

    @Override
    public SendResult sendSync(String topic, String expression, String keys, Object data) {
        return AliRocketMQUtil.tranResult(this.producer.send(AliRocketMQUtil.convert(topic, expression, keys, data, serializer)));
    }

    @Override
    public void sendAsync(String topic, String expression, String keys, Object data, AsyncCallback callback) {
        this.producer.sendOneway(AliRocketMQUtil.convert(topic, expression, keys, data, serializer));
    }

    @Override
    public SendResult syncSend(String destination, Message<?> message) {
        return AliRocketMQUtil.tranResult(this.producer.send(AliRocketMQUtil.convertToRocketMessage(destination, message, serializer)));
    }

    @Override
    public void asyncSend(String destination, Message<?> message, AsyncCallback callback) {
        this.producer.sendAsync(AliRocketMQUtil.convertToRocketMessage(destination, message, serializer), new AliCallBack(callback));
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
            this.callback.success(AliSendResult.builder().topic(sendResult.getTopic()).messageId(sendResult.getMessageId()).build());
        }

        @Override
        public void onException(OnExceptionContext context) {
            this.callback.onError(context.getException());
        }
    }


}

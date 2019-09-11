package com.anze.mq.spring.api.impl.ali;

import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.anze.mq.spring.api.AsyncCallback;
import com.anze.mq.spring.api.DataConvertor;
import com.anze.mq.spring.api.Producer;
import com.anze.mq.spring.api.SendResult;
import com.anze.mq.spring.support.RocketMQUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

/**
 * @author ZhangShaowei on 2019/9/11 16:44
 **/
@Slf4j
@AllArgsConstructor
public class AliProducerAdaptor implements Producer {

    private com.aliyun.openservices.ons.api.bean.ProducerBean producer;

    private DataConvertor convertor;

    @Override
    public void sendOneway(String topic, String expression, Object data) {
        this.producer.sendOneway(RocketMQUtil.convert(topic, expression, data, convertor));
    }

    @Override
    public SendResult sendSync(String topic, String expression, Object data) {
        return RocketMQUtil.tranResult(this.producer.send(RocketMQUtil.convert(topic, expression, data, convertor)));
    }

    @Override
    public void sendAsync(String topic, String expression, Object data, AsyncCallback callback) {
        this.producer.sendAsync(RocketMQUtil.convert(topic, expression, data, convertor), new AliCallBack(callback));
    }

    @Override
    public SendResult syncSend(String destination, Message<?> message) {
        return RocketMQUtil.tranResult(this.producer.send(RocketMQUtil.convertToRocketMessage(destination, message, convertor)));
    }

    @Override
    public void asyncSend(String destination, Message<?> message, AsyncCallback callback) {
        this.producer.sendAsync(RocketMQUtil.convertToRocketMessage(destination, message, convertor), new AliCallBack(callback));
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

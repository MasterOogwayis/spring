package com.zsw.mq.spring.api.adapter;

import com.zsw.mq.spring.api.AsyncCallback;
import com.zsw.mq.spring.api.Producer;
import com.zsw.mq.spring.api.SendResult;
import com.zsw.mq.spring.serializer.MessageSerializer;
import com.zsw.mq.spring.support.RocketMQUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;

import java.util.Objects;

/**
 * @author ZhangShaowei on 2019/9/12 11:22
 **/
@Slf4j
public class DefaultProducerAdapter implements Producer {

    private volatile Boolean running = false;

    private DefaultMQProducer producer;

    private MessageSerializer serializer;

    private final ResultConverter converter = new ResultConverter();

    public DefaultProducerAdapter(DefaultMQProducer producer, MessageSerializer serializer) {
        this.producer = producer;
        this.serializer = serializer;
    }

    @Override
    @SneakyThrows
    public void sendOneway(String topic, String tags, Object data) {
        this.producer.sendOneway(this.create(topic, tags, "", data));
    }

    @Override
    @SneakyThrows
    public SendResult sendSync(String topic, String tags, Object data) {
        return this.converter.convert(this.producer.send(this.create(topic, tags, "", data)));
    }

    @Override
    @SneakyThrows
    public void sendAsync(String topic, String tags, Object data, AsyncCallback callback) {
        this.producer.send(this.create(topic, tags, "", data), new DefaultSendCallback(callback));
    }

    @Override
    @SneakyThrows
    public void sendOneway(String topic, String tags, String keys, Object data) {
        this.producer.sendOneway(this.create(topic, tags, keys, data));
    }

    @Override
    @SneakyThrows
    public SendResult sendSync(String topic, String tags, String keys, Object data) {
        return this.converter.convert(this.producer.send(this.create(topic, tags, keys, data)));
    }

    @Override
    @SneakyThrows
    public void sendAsync(String topic, String tags, String keys, Object data, AsyncCallback callback) {
        this.producer.send(this.create(topic, tags, keys, data), new DefaultSendCallback(callback));
    }

    @Override
    @SneakyThrows
    public SendResult syncSend(String destination, Message<?> message) {
        return this.converter.convert(this.producer.send(RocketMQUtil.convertToRocketMessage(destination, message, serializer)));
    }

    @Override
    @SneakyThrows
    public void asyncSend(String destination, Message<?> message, AsyncCallback callback) {
        this.producer.send(RocketMQUtil.convertToRocketMessage(destination, message, serializer), new DefaultSendCallback(callback));
    }

    @Override
    public void start() {
        if (isRunning()) {
            return;
        }
        try {
            producer.start();
        } catch (MQClientException e) {
            throw new IllegalStateException("Failed to start RocketMQ push consumer", e);
        }
        this.running = true;
    }

    @Override
    public void stop() {
        if (this.isRunning() && Objects.nonNull(this.producer)) {
            producer.shutdown();
        }
        this.running = false;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }


    private org.apache.rocketmq.common.message.Message create(
            String topic, String tags, @Nullable String keys, Object data) {
        return new org.apache.rocketmq.common.message.Message(topic, tags, keys, this.serializer.serialize(data));
    }


    @AllArgsConstructor
    class DefaultSendCallback implements SendCallback {
        private AsyncCallback callback;


        @Override
        public void onSuccess(org.apache.rocketmq.client.producer.SendResult sendResult) {
            callback.success(DefaultProducerAdapter.this.converter.convert(sendResult));
        }

        @Override
        public void onException(Throwable e) {
            callback.onError(e);
        }
    }
}

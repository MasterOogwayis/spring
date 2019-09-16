package com.zsw.mq.spring.adapter.core;

import com.zsw.mq.spring.adapter.AsyncCallback;
import com.zsw.mq.spring.adapter.Producer;
import com.zsw.mq.spring.adapter.SendResult;
import com.zsw.mq.spring.adapter.convert.ResultConverter;
import com.zsw.mq.spring.serializer.MessageSerializer;
import com.zsw.mq.spring.support.RocketMQHeaders;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author ZhangShaowei on 2019/9/12 11:22
 **/
@Slf4j
public class DefaultProducerAdapter implements Producer {

    private volatile Boolean running = false;

    private DefaultMQProducer producer;

    private MessageSerializer serializer;

    private Converter<org.apache.rocketmq.client.producer.SendResult, SendResult> converter;

    public DefaultProducerAdapter(DefaultMQProducer producer, MessageSerializer serializer) {
        this.producer = producer;
        this.serializer = serializer;
        converter = new ResultConverter();
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
        return this.converter.convert(this.producer.send(this.convertToRocketMessage(destination, message, serializer)));
    }

    @Override
    @SneakyThrows
    public void asyncSend(String destination, Message<?> message, AsyncCallback callback) {
        this.producer.send(this.convertToRocketMessage(destination, message, serializer), new DefaultSendCallback(callback));
    }


    private org.apache.rocketmq.common.message.Message create(
            String topic, String tags, @Nullable String keys, Object data) {
        return new org.apache.rocketmq.common.message.Message(topic, tags, keys, this.serializer.serialize(data));
    }

    public org.apache.rocketmq.common.message.Message convertToRocketMessage(
            String destination, org.springframework.messaging.Message<?> message, MessageSerializer serializer) {
        Object payloadObj = message.getPayload();
        byte[] payloads = serializer.serialize(payloadObj);

        String[] tempArr = destination.split(":", 2);
        String topic = tempArr[0];
        String tags = "";
        if (tempArr.length > 1) {
            tags = tempArr[1];
        }

        org.apache.rocketmq.common.message.Message rocketMsg = new org.apache.rocketmq.common.message.Message(topic, tags, payloads);

        MessageHeaders headers = message.getHeaders();
        if (!CollectionUtils.isEmpty(headers)) {
            Object keys = headers.get(RocketMQHeaders.KEYS);
            // if headers has 'KEYS', set rocketMQ message key
            if (!StringUtils.isEmpty(keys)) {
                rocketMsg.setKeys(keys.toString());
            }
            // FIXME
            Object flagObj = headers.getOrDefault("FLAG", "0");
            int flag = 0;
            try {
                flag = Integer.parseInt(flagObj.toString());
            } catch (NumberFormatException e) {
                // Ignore it
                log.info("flag must be integer, flagObj:{}", flagObj);
            }
            rocketMsg.setFlag(flag);

            Object waitStoreMsgOkObj = headers.getOrDefault("WAIT_STORE_MSG_OK", "true");
            rocketMsg.setWaitStoreMsgOK(Boolean.TRUE.equals(waitStoreMsgOkObj));

            headers.entrySet().stream()
                    .filter(entry -> !Objects.equals(entry.getKey(), "FLAG")
                            && !Objects.equals(entry.getKey(), "WAIT_STORE_MSG_OK")) // exclude "FLAG", "WAIT_STORE_MSG_OK"
                    .forEach(entry -> {
                        if (!MessageConst.STRING_HASH_SET.contains(entry.getKey())) {
                            rocketMsg.putUserProperty(entry.getKey(), String.valueOf(entry.getValue()));
                        }
                    });
        }

        return rocketMsg;
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

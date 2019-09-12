/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zsw.mq.spring.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsw.mq.spring.api.AsyncCallback;
import com.zsw.mq.spring.api.Producer;
import com.zsw.mq.spring.api.SendResult;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.core.AbstractMessageSendingTemplate;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import java.util.Map;
import java.util.Objects;

/**
 * 仅支持 3 种基本发送类型，至于事务消息...再说
 */
@Setter
@Getter
@SuppressWarnings({"WeakerAccess", "unused"})
public class RocketMQTemplate extends AbstractMessageSendingTemplate<String> implements InitializingBean, DisposableBean {
    private static final Logger log = LoggerFactory.getLogger(RocketMQTemplate.class);

    private Producer producer;


    private ObjectMapper objectMapper;


    /**
     * Same to {@link #syncSend(String, Message)} with send timeout specified in addition.
     *
     * @param destination formats: `topicName:tags`
     * @param message     {@link org.springframework.messaging.Message}
     * @return {@link SendResult}
     */
    public SendResult syncSend(String destination, Message<?> message) {
        if (Objects.isNull(message) || Objects.isNull(message.getPayload())) {
            log.error("syncSend failed. destination:{}, message is null ", destination);
            throw new IllegalArgumentException("`message` and `message.payload` cannot be null");
        }

        try {
            long now = System.currentTimeMillis();
            SendResult sendResult = producer.syncSend(destination, message);
            long costTime = System.currentTimeMillis() - now;
            log.debug("send message cost: {} ms, msgId:{}", costTime, sendResult.getMessageId());
            return sendResult;
        } catch (Exception e) {
            log.error("syncSend failed. destination:{}, message:{} ", destination, message);
            throw new MessagingException(e.getMessage(), e);
        }
    }


    /**
     * Same to {@link #syncSend(String, Object)} with send timeout specified in addition.
     *
     * @param destination formats: `topicName:tags`
     * @param payload     the Object to use as payload
     * @return {@link SendResult}
     */
    public SendResult syncSend(String destination, Object payload) {
        Message<?> message = this.doConvert(payload, null, null);
        return syncSend(destination, message);
    }

    /**
     * Same to {@link #asyncSend(String, Message, AsyncCallback)} with send timeout and delay level specified in addition.
     *
     * @param destination  formats: `topicName:tags`
     * @param message      {@link org.springframework.messaging.Message}
     * @param sendCallback {@link AsyncCallback}
     */
    public void asyncSend(String destination, Message<?> message, AsyncCallback sendCallback) {
        if (Objects.isNull(message) || Objects.isNull(message.getPayload())) {
            log.error("asyncSend failed. destination:{}, message is null ", destination);
            throw new IllegalArgumentException("`message` and `message.payload` cannot be null");
        }

        try {
            producer.asyncSend(destination, message, sendCallback);
        } catch (Exception e) {
            log.info("asyncSend failed. destination:{}, message:{} ", destination, message);
            throw new MessagingException(e.getMessage(), e);
        }
    }

    public void sendOneway(String topic, String tags, Object data) {
        this.producer.sendOneway(topic, tags, data);
    }

    public SendResult sendSync(String topic, String tags, Object data) {
        return this.producer.sendSync(topic, tags, data);
    }

    public void sendAsync(String topic, String tags, Object data, AsyncCallback callback) {
        this.producer.sendAsync(topic, tags, data, callback);
    }

    /**
     * Same to {@link #asyncSend(String, Message, AsyncCallback)}.
     *
     * @param destination  formats: `topicName:tags`
     * @param payload      the Object to use as payload
     * @param sendCallback {@link AsyncCallback}
     */
    public void asyncSend(String destination, Object payload, AsyncCallback sendCallback) {
        Message<?> message = this.doConvert(payload, null, null);
        asyncSend(destination, message, sendCallback);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
//        if (producer != null) {
//            producer.start();
//        }
    }

    @Override
    protected void doSend(String destination, Message<?> message) {
        SendResult sendResult = syncSend(destination, message);
        log.debug("send message to `{}` finished. result:{}", destination, sendResult);
    }


    @Override
    protected Message<?> doConvert(Object payload, Map<String, Object> headers, MessagePostProcessor postProcessor) {
        String content;
        if (payload instanceof String) {
            content = (String) payload;
        } else {
            // If payload not as string, use objectMapper change it.
            try {
                content = objectMapper.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
                log.error("serialize payload to String failed. payload:{}", payload);
                throw new RuntimeException("serialize to payload to String failed.", e);
            }
        }

        MessageBuilder<?> builder = MessageBuilder.withPayload(payload);
        if (headers != null) {
            builder.copyHeaders(headers);
        }
        builder.setHeaderIfAbsent(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN);

        Message<?> message = builder.build();
        if (postProcessor != null) {
            message = postProcessor.postProcessMessage(message);
        }
        return message;
    }

    @Override
    public void destroy() {
        if (Objects.nonNull(producer) && this.producer.isRunning()) {
            this.producer.stop();
        }
    }


}

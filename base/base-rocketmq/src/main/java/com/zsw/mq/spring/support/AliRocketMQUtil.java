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
package com.zsw.mq.spring.support;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.common.message.MessageConst;
import com.zsw.mq.spring.api.SendResult;
import com.zsw.mq.spring.api.adapter.AliSendResult;
import com.zsw.mq.spring.serializer.MessageSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Properties;

@Slf4j
public class AliRocketMQUtil {

    public static String toRocketHeaderKey(String rawKey) {
        return RocketMQHeaders.PREFIX + rawKey;
    }

    private static void addUserProperties(Properties properties, MessageBuilder messageBuilder) {
        if (!CollectionUtils.isEmpty(properties)) {
            properties.forEach((oKey, val) -> {
                String key = oKey.toString();
                if (!MessageConst.STRING_HASH_SET.contains(key) && !MessageHeaders.ID.equals(key)
                        && !MessageHeaders.TIMESTAMP.equals(key) &&
                        (!key.startsWith(RocketMQHeaders.PREFIX) || !MessageConst.STRING_HASH_SET.contains(key.replaceFirst("^" + RocketMQHeaders.PREFIX, "")))) {
                    messageBuilder.setHeader(key, val);
                }
            });
        }
    }

    public static org.springframework.messaging.Message convertToSpringMessage(
            Message message) {
        MessageBuilder messageBuilder =
                MessageBuilder.withPayload(message.getBody()).
                        setHeader(toRocketHeaderKey(RocketMQHeaders.KEYS), message.getKey()).
                        setHeader(toRocketHeaderKey(RocketMQHeaders.TAGS), message.getTag()).
                        setHeader(toRocketHeaderKey(RocketMQHeaders.TOPIC), message.getTopic());
        addUserProperties(message.getUserProperties(), messageBuilder);
        return messageBuilder.build();
    }

    public static Message convertToRocketMessage(
            String destination,
            org.springframework.messaging.Message<?> message,
            MessageSerializer convertor) {
        byte[] payloads = convertor.serialize(message.getPayload());

        String[] tempArr = destination.split(":", 2);
        String topic = tempArr[0];
        String tags = "";
        if (tempArr.length > 1) {
            tags = tempArr[1];
        }

        Message rocketMsg = new Message(topic, tags, payloads);

        MessageHeaders headers = message.getHeaders();
        if (Objects.nonNull(headers) && !headers.isEmpty()) {
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


    public static com.aliyun.openservices.ons.api.Message convert(
            String topic,
            String expression,
            String key,
            Object data,
            MessageSerializer convertor) {
        com.aliyun.openservices.ons.api.Message message = new com.aliyun.openservices.ons.api.Message();
        message.setTopic(topic);
        message.setTag(expression);
        message.setBody(convertor.serialize(data));
        if (StringUtils.hasText(key)) {
            message.setKey(key);
        }
        return message;
    }


    public static SendResult tranResult(com.aliyun.openservices.ons.api.SendResult sendResult) {
        return AliSendResult.builder().topic(sendResult.getTopic()).messageId(sendResult.getMessageId()).build();
    }

}

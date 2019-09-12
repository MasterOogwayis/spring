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

import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@SuppressWarnings("WeakerAccess")
@Slf4j
@Setter
public class AliRocketMQListenerContainer extends AbstractMQListenerContainer {

    private long suspendCurrentQueueTimeMillis = 1000;

    /**
     * Message consume retry strategy<br> -1,no retry,put into DLQ directly<br> 0,broker control retry frequency<br>
     * >0,client control retry frequency.
     */
    private int delayLevelWhenNextConsume = 0;

    private ConsumerBean consumer;

    protected void validate() {
        Assert.notNull(rocketMQListener, "Property 'rocketMQListener' is required");
        Assert.notNull(consumerGroup, "Property 'consumerGroup' is required");
        Assert.notNull(nameServer, "Property 'nameServer' is required");
        Assert.notNull(topic, "Property 'topic' is required");
        Assert.notNull(accessKey, "Property 'accessKey' is required");
        Assert.notNull(secretKey, "Property 'secretKey' is required");
    }



    @Override
    protected void initConsumer() {
        resolveNeededProperties();
        validate();

        //配置文件
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, this.consumerGroup);
        properties.setProperty(PropertyKeyConst.AccessKey, accessKey);
        properties.setProperty(PropertyKeyConst.SecretKey, secretKey);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, nameServer);

        properties.setProperty(PropertyKeyConst.InstanceName, this.name);
        // 模式
        properties.setProperty(PropertyKeyConst.MessageModel, this.messageModel.getModeCN());
        //将消费者线程数 大小相同
        properties.setProperty(PropertyKeyConst.ConsumeThreadNums, String.valueOf(this.consumeThreadMax));
        properties.setProperty(PropertyKeyConst.ConsumeTimeout, String.valueOf(this.consumeTimeout));
        //订阅关系
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>();
        Subscription subscription = new Subscription();
        subscription.setTopic(topic);
        subscription.setExpression(selectorExpression);
        subscriptionTable.put(subscription, new DefaultMessageListener());
        //订阅多个topic如上面设置
        this.consumer = new ConsumerBean();
        this.consumer.setProperties(properties);
        this.consumer.setSubscriptionTable(subscriptionTable);
    }

    @Override
    public void destroy() {
        if (Objects.nonNull(consumer) && this.isRunning()) {
            consumer.shutdown();
            log.info("container destroyed, {}", this.toString());
        }
    }

    @Override
    public void start() {
        if (this.isRunning()) {
            log.warn("container already running. {}", this.toString());
            return;
        }
        consumer.start();
        this.running = true;
        log.info("running container: {}", this.toString());
    }

    @Override
    public void stop() {
        if (this.isRunning() && Objects.nonNull(consumer)) {
            consumer.shutdown();
        }
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }


    public class DefaultMessageListener implements MessageListener {

        @Override
        public Action consume(Message message, ConsumeContext context) {
            log.debug("received msg: {}", message);
            try {
                long now = System.currentTimeMillis();
                //noinspection unchecked
                rocketMQListener.onMessage(doConvertMessage(message));
                long costTime = System.currentTimeMillis() - now;
                log.info("consume {} cost: {} ms", message.getMsgID(), costTime);
            } catch (Exception e) {
                log.warn("consume message failed. message:{}", message, e);
                return Action.ReconsumeLater;
            }
            return Action.CommitMessage;
        }
    }

    @SuppressWarnings("unchecked")
    private Object doConvertMessage(Message message) {
        if (Objects.equals(messageType, Message.class)) {
            return message;
        } else {
            // If msgType not string, use objectMapper change it.
            try {
                return this.serializer.deserialize(message.getBody(), messageType);
            } catch (Exception e) {
                throw new RuntimeException("cannot serialize message to " + messageType, e);
            }
        }
    }


    @Override
    public String toString() {
        return "AliRocketMQListenerContainer{" +
                "consumerGroup='" + consumerGroup + '\'' +
                ", nameServer='" + nameServer + '\'' +
                ", topic='" + topic + '\'' +
                ", consumeMode=" + consumeMode +
                ", selectorType=" + selectorType +
                ", selectorExpression='" + selectorExpression + '\'' +
                ", messageModel=" + messageModel +
                '}';
    }


}

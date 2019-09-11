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

package com.anze.mq.spring.support;

import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.exception.MQClientException;
import com.anze.mq.spring.annotation.ConsumeMode;
import com.anze.mq.spring.annotation.MessageModel;
import com.anze.mq.spring.annotation.RocketMQMessageListener;
import com.anze.mq.spring.annotation.SelectorType;
import com.anze.mq.spring.autoconfigure.RocketMQProperties;
import com.anze.mq.spring.core.RocketMQListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@SuppressWarnings("WeakerAccess")
@Setter
public class DefaultRocketMQListenerContainer implements InitializingBean,
        RocketMQListenerContainer, SmartLifecycle, EnvironmentAware {
    private final static Logger log = LoggerFactory.getLogger(DefaultRocketMQListenerContainer.class);

    /**
     * The name of the DefaultRocketMQListenerContainer instance
     */
    private String name;

    private long suspendCurrentQueueTimeMillis = 1000;

    /**
     * Message consume retry strategy<br> -1,no retry,put into DLQ directly<br> 0,broker control retry frequency<br>
     * >0,client control retry frequency.
     */
    private int delayLevelWhenNextConsume = 0;

    private Environment environment;

    private String nameServer;

    private String consumerGroup;

    private String topic;

    private String accessKey;

    private String secretKey;

    private int consumeThreadMax = 64;

    private String charset = "UTF-8";

    private ObjectMapper objectMapper;

    private RocketMQProperties properties;

    private RocketMQListener rocketMQListener;

    private RocketMQMessageListener annotation;

    private Consumer consumer;

    private Class messageType;

    private volatile boolean running;

    // The following properties came from @RocketMQMessageListener.
    private ConsumeMode consumeMode;
    private SelectorType selectorType;
    private String selectorExpression;
    private MessageModel messageModel;
    private long consumeTimeout;
    private String tags;




    @Override
    public void setupMessageListener(RocketMQListener rocketMQListener) {
        this.rocketMQListener = rocketMQListener;
    }

    @Override
    public void destroy() {
        this.setRunning(false);
        if (Objects.nonNull(consumer)) {
            consumer.shutdown();
        }
        log.info("container destroyed, {}", this.toString());
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        stop();
        callback.run();
    }

    @Override
    public void start() {
        if (this.isRunning()) {
            throw new IllegalStateException("container already running. " + this.toString());
        }

        consumer.start();
        this.setRunning(true);

        log.info("running container: {}", this.toString());
    }

    @Override
    public void stop() {
        if (this.isRunning()) {
            if (Objects.nonNull(consumer)) {
                consumer.shutdown();
            }
            setRunning(false);
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    private void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public int getPhase() {
        // Returning Integer.MAX_VALUE only suggests that
        // we will be the first bean to shutdown and last bean to start
        return Integer.MAX_VALUE;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        resolveNeededProperties();
        validate();
        initRocketMQPushConsumer();
        this.messageType = getMessageType();
        log.debug("RocketMQ messageType: {}", messageType.getName());
    }

    private void validate() {
        Assert.notNull(rocketMQListener, "Property 'rocketMQListener' is required");
        Assert.notNull(consumerGroup, "Property 'consumerGroup' is required");
        Assert.notNull(nameServer, "Property 'nameServer' is required");
        Assert.notNull(topic, "Property 'topic' is required");
        Assert.notNull(accessKey, "Property 'accessKey' is required");
        Assert.notNull(secretKey, "Property 'secretKey' is required");
    }

    private void resolveNeededProperties() {
        //        this.rocketMQMessageListener = annotation;
        this.consumeMode = annotation.consumeMode();
        this.consumeThreadMax = annotation.consumeThreadMax();
        this.messageModel = annotation.messageModel();
        this.selectorExpression = annotation.selectorExpression();
        this.tags = annotation.tags();
        this.selectorType = annotation.selectorType();
        this.consumeTimeout = annotation.consumeTimeout();
        this.consumeThreadMax = annotation.consumeThreadMax();

        this.nameServer = environment.resolvePlaceholders(this.annotation.nameServer());
        this.nameServer = StringUtils.isEmpty(this.nameServer) ? this.properties.getNameServer() : this.nameServer;

        this.topic = this.environment.resolvePlaceholders(this.annotation.topic());
        this.accessKey = this.environment.resolvePlaceholders(this.annotation.accessKey());
        this.secretKey = this.environment.resolvePlaceholders(this.annotation.secretKey());
        this.consumerGroup = this.environment.resolvePlaceholders(this.annotation.consumerGroup());
    }

    @Override
    public String toString() {
        return "DefaultRocketMQListenerContainer{" +
                "consumerGroup='" + consumerGroup + '\'' +
                ", nameServer='" + nameServer + '\'' +
                ", topic='" + topic + '\'' +
                ", consumeMode=" + consumeMode +
                ", selectorType=" + selectorType +
                ", selectorExpression='" + selectorExpression + '\'' +
                ", messageModel=" + messageModel +
                '}';
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
            String str = new String(message.getBody(), Charset.forName(charset));
            if (Objects.equals(messageType, String.class)) {
                return str;
            } else {
                // If msgType not string, use objectMapper change it.
                try {
                    return objectMapper.readValue(str, messageType);
                } catch (Exception e) {
                    log.info("convert failed. str:{}, msgType:{}", str, messageType);
                    throw new RuntimeException("cannot convert message to " + messageType, e);
                }
            }
        }
    }

    private Class getMessageType() {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(rocketMQListener);
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)) {
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (Objects.nonNull(interfaces)) {
            for (Type type : interfaces) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    if (Objects.equals(parameterizedType.getRawType(), RocketMQListener.class)) {
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                            return (Class) actualTypeArguments[0];
                        } else {
                            return Object.class;
                        }
                    }
                }
            }

            return Object.class;
        } else {
            return Object.class;
        }
    }


    private void initRocketMQPushConsumer() throws MQClientException {

        ConsumerBean consumerBean = new ConsumerBean();
        //配置文件
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.AccessKey, accessKey);
        properties.setProperty(PropertyKeyConst.SecretKey, secretKey);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, nameServer);

        properties.setProperty(PropertyKeyConst.GROUP_ID, this.consumerGroup);
        properties.setProperty(PropertyKeyConst.InstanceName, this.name);
        // 模式
        properties.setProperty(PropertyKeyConst.MessageModel, this.messageModel.getModeCN());
        //将消费者线程数 大小相同
        properties.setProperty(PropertyKeyConst.ConsumeThreadNums, String.valueOf(this.consumeThreadMax));
        properties.setProperty(PropertyKeyConst.ConsumeTimeout, String.valueOf(this.consumeTimeout));
        consumerBean.setProperties(properties);
        //订阅关系
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>();
        Subscription subscription = new Subscription();
        subscription.setTopic(topic);
        subscription.setExpression(selectorExpression);
        subscriptionTable.put(subscription, new DefaultMessageListener());
        //订阅多个topic如上面设置

        consumerBean.setSubscriptionTable(subscriptionTable);


        consumer = consumerBean;

    }


}

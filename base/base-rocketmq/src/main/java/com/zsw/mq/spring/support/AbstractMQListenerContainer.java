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

import com.zsw.mq.spring.annotation.ConsumeMode;
import com.zsw.mq.spring.annotation.MessageModel;
import com.zsw.mq.spring.annotation.RocketMQMessageListener;
import com.zsw.mq.spring.annotation.SelectorType;
import com.zsw.mq.spring.autoconfigure.MessageSerializer;
import com.zsw.mq.spring.autoconfigure.RocketMQProperties;
import com.zsw.mq.spring.core.RocketMQListener;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
@Slf4j
@Setter
public abstract class AbstractMQListenerContainer implements InitializingBean,
        RocketMQListenerContainer, SmartLifecycle, EnvironmentAware {

    /**
     * The name of the DefaultRocketMQListenerContainer instance
     */
    protected String name;

    protected String nameServer;

    protected String consumerGroup;

    protected String topic;

    protected String accessKey;

    protected String secretKey;

    protected Environment environment;

    protected RocketMQProperties properties;

    protected MessageSerializer serializer;

    protected RocketMQListener rocketMQListener;

    protected RocketMQMessageListener annotation;

    protected Class messageType;

    /**
     * The following properties came from @RocketMQMessageListener.
     */
    protected ConsumeMode consumeMode;
    protected SelectorType selectorType;
    protected String selectorExpression;
    protected MessageModel messageModel;
    protected long consumeTimeout;
    protected String tags;
    protected int consumeThreadMax = 64;


    protected volatile boolean running = false;


    @Override
    public void setupMessageListener(RocketMQListener rocketMQListener) {
        this.rocketMQListener = rocketMQListener;
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
    public int getPhase() {
        // Returning Integer.MAX_VALUE only suggests that
        // we will be the first bean to shutdown and last bean to start
        return Integer.MAX_VALUE;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        initConsumer();
        this.messageType = getMessageType();
        log.debug("RocketMQ messageType: {}", messageType.getName());
    }


    protected void resolveNeededProperties() {
        //        this.rocketMQMessageListener = annotation;
        this.consumeMode = annotation.consumeMode();
        this.consumeThreadMax = annotation.consumeThreadMax();
        this.messageModel = annotation.messageModel();
        this.selectorExpression = annotation.selectorExpression();
        this.tags = annotation.tags();
        this.selectorType = annotation.selectorType();
        this.consumeTimeout = annotation.consumeTimeout();

        this.nameServer = environment.resolvePlaceholders(this.annotation.nameServer());
        this.nameServer = StringUtils.isEmpty(this.nameServer) ? this.properties.getNameServer() : this.nameServer;

        this.topic = this.environment.resolvePlaceholders(this.annotation.topic());
        this.accessKey = this.environment.resolvePlaceholders(this.annotation.accessKey());
        this.secretKey = this.environment.resolvePlaceholders(this.annotation.secretKey());
        this.consumerGroup = this.environment.resolvePlaceholders(this.annotation.consumerGroup());
    }


    protected abstract void initConsumer();

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


}

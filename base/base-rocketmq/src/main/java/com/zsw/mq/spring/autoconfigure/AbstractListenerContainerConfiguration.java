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

package com.zsw.mq.spring.autoconfigure;

import com.zsw.mq.spring.annotation.ConsumeMode;
import com.zsw.mq.spring.annotation.MessageModel;
import com.zsw.mq.spring.annotation.RocketMQMessageListener;
import com.zsw.mq.spring.core.RocketMQListener;
import com.zsw.mq.spring.serializer.MessageSerializer;
import com.zsw.mq.spring.support.bean.AbstractMQListenerContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.StandardEnvironment;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static com.zsw.mq.spring.config.RocketMQConfigUtils.PREFIX;

@Slf4j
public abstract class AbstractListenerContainerConfiguration implements ApplicationContextAware, SmartInitializingSingleton {

    private ConfigurableApplicationContext applicationContext;

    private AtomicLong counter = new AtomicLong(0);

    protected StandardEnvironment environment;

    protected RocketMQProperties rocketMQProperties;

    protected MessageSerializer serializer;


    public AbstractListenerContainerConfiguration(MessageSerializer serializer,
                                                  StandardEnvironment environment,
                                                  RocketMQProperties rocketMQProperties) {
        this.serializer = serializer;
        this.environment = environment;
        this.rocketMQProperties = rocketMQProperties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> beans = this.applicationContext.getBeansWithAnnotation(RocketMQMessageListener.class);
        beans.forEach(this::registerContainer);
    }

    /**
     * FIXME aliyum rocketmq 可以实现相同 groupName 的 consumer 订阅不同的 tpoic
     *
     * @param beanName
     * @param bean
     */
    protected void registerContainer(String beanName, Object bean) {
        Class<?> clazz = AopProxyUtils.ultimateTargetClass(bean);

        if (!RocketMQListener.class.isAssignableFrom(bean.getClass())) {
            throw new IllegalStateException(clazz + " is not instance of " + RocketMQListener.class.getName());
        }

        RocketMQMessageListener annotation = clazz.getAnnotation(RocketMQMessageListener.class);

        String consumerGroup = this.environment.resolvePlaceholders(annotation.consumerGroup());
        String topic = this.environment.resolvePlaceholders(annotation.topic());

        boolean listenerEnabled =
                (boolean) rocketMQProperties.getConsumer().getListeners().getOrDefault(consumerGroup, Collections.EMPTY_MAP)
                        .getOrDefault(topic, true);

        if (!listenerEnabled) {
            log.debug(
                    "Consumer Listener (group:{},topic:{}) is not enabled by configuration, will ignore initialization.",
                    consumerGroup, topic);
            return;
        }
        validate(annotation);

        Class containerClazz = getContainerClazz();
        String containerBeanName = String.format("%s_%s",
                containerClazz.getName(), counter.incrementAndGet());

        String appName = this.environment.resolvePlaceholders(annotation.instanceName());

        RootBeanDefinition beanDefinition = new RootBeanDefinition(containerClazz);
        beanDefinition.getPropertyValues().add("name", appName + PREFIX + beanName);
        beanDefinition.getPropertyValues().add("rocketMQListener", bean);
        beanDefinition.getPropertyValues().add("annotation", annotation);
        beanDefinition.getPropertyValues().add("serializer", serializer);
        beanDefinition.getPropertyValues().add("environment", environment);
        beanDefinition.getPropertyValues().add("properties", rocketMQProperties);

        GenericApplicationContext genericApplicationContext = (GenericApplicationContext) applicationContext;
        genericApplicationContext.registerBeanDefinition(containerBeanName, beanDefinition);


        log.info("Register the listener to container, listenerBeanName:{}, containerBeanName:{}", beanName, containerBeanName);
    }


    abstract Class<? extends AbstractMQListenerContainer> getContainerClazz();

    private void validate(RocketMQMessageListener annotation) {
        if (annotation.consumeMode() == ConsumeMode.ORDERLY &&
                annotation.messageModel() == MessageModel.BROADCASTING) {
            throw new BeanDefinitionValidationException(
                    "Bad annotation definition in @RocketMQMessageListener, messageModel BROADCASTING does not support ORDERLY message!");
        }
    }

}

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

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsw.mq.spring.annotation.ExtRocketMQTemplateConfiguration;
import com.zsw.mq.spring.adapter.Producer;
import com.zsw.mq.spring.adapter.core.AliProducerAdapter;
import com.zsw.mq.spring.core.RocketMQTemplate;
import com.zsw.mq.spring.serializer.MessageSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;


/**
 * 暂不支持自动生成多个 RocketMQTemplate
 */
//@Configuration
public class ExtProducerResetConfiguration implements ApplicationContextAware, SmartInitializingSingleton {
    private final static Logger log = LoggerFactory.getLogger(ExtProducerResetConfiguration.class);

    private ConfigurableApplicationContext applicationContext;

    private StandardEnvironment environment;

    private RocketMQProperties rocketMQProperties;

    private ObjectMapper objectMapper;

    private MessageSerializer serializer;

    public ExtProducerResetConfiguration(
            ObjectMapper rocketMQMessageObjectMapper,
            StandardEnvironment environment,
            RocketMQProperties rocketMQProperties,
            MessageSerializer serializer) {
        this.objectMapper = rocketMQMessageObjectMapper;
        this.environment = environment;
        this.rocketMQProperties = rocketMQProperties;
        this.serializer = serializer;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> beans = this.applicationContext.getBeansWithAnnotation(ExtRocketMQTemplateConfiguration.class);

        if (Objects.nonNull(beans)) {
            beans.forEach(this::registerTemplate);
        }
    }

    private void registerTemplate(String beanName, Object bean) {
        Class<?> clazz = AopProxyUtils.ultimateTargetClass(bean);

        if (!RocketMQTemplate.class.isAssignableFrom(bean.getClass())) {
            throw new IllegalStateException(clazz + " is not instance of " + RocketMQTemplate.class.getName());
        }

        ExtRocketMQTemplateConfiguration annotation = clazz.getAnnotation(ExtRocketMQTemplateConfiguration.class);
        GenericApplicationContext genericApplicationContext = (GenericApplicationContext) applicationContext;
        validate(annotation, genericApplicationContext);

        Producer mqProducer = createProducer(annotation, beanName);
        // Set instanceName same as the beanName
        mqProducer.start();
        RocketMQTemplate rocketMQTemplate = (RocketMQTemplate) bean;
        rocketMQTemplate.setProducer(mqProducer);
        rocketMQTemplate.setObjectMapper(objectMapper);


        log.info("Set real producer to :{} {}", beanName, annotation.value());
    }

    private Producer createProducer(ExtRocketMQTemplateConfiguration annotation, String name) {


        String nameServer = environment.resolvePlaceholders(annotation.nameServer());
        String groupName = environment.resolvePlaceholders(annotation.group());

        RocketMQProperties.Producer producerConfig = rocketMQProperties.getProducer();
        if (producerConfig == null) {
            producerConfig = new RocketMQProperties.Producer();
        }
        groupName = StringUtils.isEmpty(groupName) ? producerConfig.getGroup() : groupName;

        String ak = environment.resolvePlaceholders(annotation.accessKey());
        ak = StringUtils.isEmpty(ak) ? producerConfig.getAccessKey() : annotation.accessKey();
        String sk = environment.resolvePlaceholders(annotation.secretKey());
        sk = StringUtils.isEmpty(sk) ? producerConfig.getSecretKey() : annotation.secretKey();

        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, groupName);
        properties.setProperty(PropertyKeyConst.AccessKey, ak);
        properties.setProperty(PropertyKeyConst.SecretKey, sk);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, nameServer);
        properties.setProperty(PropertyKeyConst.InstanceName, name);

        long sendMsgTimeout = annotation.sendMessageTimeout() == -1 ? producerConfig.getSendMessageTimeout() : annotation.sendMessageTimeout();
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, String.valueOf(sendMsgTimeout));

        ProducerBean producerBean = new ProducerBean();
        producerBean.setProperties(properties);


        return new AliProducerAdapter(producerBean, serializer);
    }

    private void validate(ExtRocketMQTemplateConfiguration annotation, GenericApplicationContext genericApplicationContext) {
        if (genericApplicationContext.isBeanNameInUse(annotation.value())) {
            throw new BeanDefinitionValidationException(String.format("Bean {} has been used in Spring Application Context, " +
                            "please check the @ExtRocketMQTemplateConfiguration",
                    annotation.value()));
        }

        if (rocketMQProperties.getNameServer() == null ||
                rocketMQProperties.getNameServer().equals(environment.resolvePlaceholders(annotation.nameServer()))) {
            throw new BeanDefinitionValidationException(
                    "Bad annotation definition in @ExtRocketMQTemplateConfiguration, nameServer property is same with " +
                            "global property, please use the default RocketMQTemplate!");
        }
    }
}
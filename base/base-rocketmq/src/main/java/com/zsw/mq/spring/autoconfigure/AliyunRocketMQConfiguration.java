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
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.MQAdmin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsw.mq.spring.api.Producer;
import com.zsw.mq.spring.api.adaptor.AliProducerAdaptor;
import com.zsw.mq.spring.config.RocketMQConfigUtils;
import com.zsw.mq.spring.core.RocketMQTemplate;
import com.zsw.mq.spring.serializer.MessageSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Properties;

import static com.zsw.mq.spring.config.RocketMQConfigUtils.PREFIX;

@Profile("aliyun")
@Configuration
@EnableConfigurationProperties(RocketMQProperties.class)
@ConditionalOnClass(MQAdmin.class)
@ConditionalOnProperty(prefix = "rocketmq", value = "name-server", matchIfMissing = true)
@Import({AliyunListenerContainerConfiguration.class})
@AutoConfigureAfter(JacksonAutoConfiguration.class)
public class AliyunRocketMQConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AliyunRocketMQConfiguration.class);

    @Autowired
    private Environment environment;

    @Autowired
    private MessageSerializer serializer;

    @PostConstruct
    public void checkProperties() {
        String nameServer = environment.getProperty("rocketmq.name-server", String.class);
        log.debug("rocketmq.nameServer = {}", nameServer);
        if (nameServer == null) {
            log.warn("The necessary spring property 'rocketmq.name-server' is not defined, all rockertmq beans creation are skipped!");
        }
    }


    @Bean(initMethod = "start", destroyMethod = "stop")
    @ConditionalOnMissingBean(Producer.class)
    @ConditionalOnProperty(prefix = "rocketmq", value = {"name-server", "producer.group"})
    public Producer defaultMQProducer(RocketMQProperties rocketMQProperties) {
        RocketMQProperties.Producer producerConfig = rocketMQProperties.getProducer();
        String nameServer = rocketMQProperties.getNameServer();
        String groupName = producerConfig.getGroup();
        Assert.hasText(nameServer, "[rocketmq.name-server] must not be null");
        Assert.hasText(groupName, "[rocketmq.producer.group] must not be null");


        String ak = rocketMQProperties.getProducer().getAccessKey();
        String sk = rocketMQProperties.getProducer().getSecretKey();


        Properties properties = new Properties();
//        properties.setProperty(PropertyKeyConst.GROUP_ID, groupName);
        properties.setProperty(PropertyKeyConst.AccessKey, ak);
        properties.setProperty(PropertyKeyConst.SecretKey, sk);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, nameServer);

        String appName = this.environment.resolvePlaceholders("${spring.application.name}");
        properties.setProperty(PropertyKeyConst.InstanceName, appName + PREFIX + "defaultMQProducer");

        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, String.valueOf(producerConfig.getSendMessageTimeout()));

        ProducerBean producerBean = new ProducerBean();
        producerBean.setProperties(properties);

        return new AliProducerAdaptor(producerBean, serializer);
    }

    @Bean(destroyMethod = "destroy")
    @ConditionalOnBean(Producer.class)
    @ConditionalOnMissingBean(name = RocketMQConfigUtils.ROCKETMQ_TEMPLATE_DEFAULT_GLOBAL_NAME)
    public RocketMQTemplate rocketMQTemplate(Producer mqProducer, ObjectMapper rocketMQMessageObjectMapper) {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(mqProducer);
        rocketMQTemplate.setObjectMapper(rocketMQMessageObjectMapper);
        return rocketMQTemplate;
    }

}

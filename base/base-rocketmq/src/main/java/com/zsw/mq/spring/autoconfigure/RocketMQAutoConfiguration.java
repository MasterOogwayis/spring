package com.zsw.mq.spring.autoconfigure;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.MQAdmin;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zsw.mq.spring.api.Producer;
import com.zsw.mq.spring.api.adapter.AliProducerAdapter;
import com.zsw.mq.spring.api.adapter.DefaultProducerAdapter;
import com.zsw.mq.spring.config.RocketMQConfigUtils;
import com.zsw.mq.spring.core.RocketMQTemplate;
import com.zsw.mq.spring.serializer.JacksonMessageSerializer;
import com.zsw.mq.spring.serializer.MessageSerializer;
import com.zsw.mq.spring.support.bean.AbstractMQListenerContainer;
import com.zsw.mq.spring.support.bean.AliRocketMQListenerContainer;
import com.zsw.mq.spring.support.bean.DefaultRocketMQListenerContainer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
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
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Properties;

import static com.zsw.mq.spring.config.RocketMQConfigUtils.PREFIX;

/**
 * @author ZhangShaowei on 2019/9/12 11:19
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(RocketMQProperties.class)
@ConditionalOnProperty(prefix = "rocketmq", value = "name-server", matchIfMissing = true)
@AutoConfigureAfter(JacksonAutoConfiguration.class)
public class RocketMQAutoConfiguration {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void checkProperties() {
        String nameServer = environment.getProperty("rocketmq.name-server", String.class);
        log.debug("rocketmq.nameServer = {}", nameServer);
        if (nameServer == null) {
            log.warn("The necessary spring property 'rocketmq.name-server' is not defined, all rockertmq beans creation are skipped!");
        }
    }

    @Bean(destroyMethod = "destroy")
    @ConditionalOnBean(Producer.class)
    @ConditionalOnMissingBean(name = RocketMQConfigUtils.ROCKETMQ_TEMPLATE_DEFAULT_GLOBAL_NAME)
    public RocketMQTemplate rocketMQTemplate(Producer producer, ObjectMapper objectMapper) {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(producer);
        rocketMQTemplate.setObjectMapper(objectMapper);
        return rocketMQTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(MessageSerializer.class)
    public MessageSerializer messageSerializer(ObjectMapper objectMapper) {
        return new JacksonMessageSerializer(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //忽略没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //允许没有双引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //允许转义字符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        //允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return mapper;
    }

//    @Profile(PROFILE_DEFAULT)
    @ConditionalOnClass(org.apache.rocketmq.client.MQAdmin.class)
    @ConditionalOnProperty(prefix = "rocketmq", value = {"name-server", "producer.group"})
    @Import({DefaultListenerContainerConfiguration.class})
    public class DefaultConfiguration {

        @Bean(initMethod = "start", destroyMethod = "stop")
        public Producer defaultMQProducer(
                RocketMQProperties rocketMQProperties, MessageSerializer serializer) {
            RocketMQProperties.Producer producerConfig = rocketMQProperties.getProducer();
            String nameServer = rocketMQProperties.getNameServer();
            String groupName = producerConfig.getGroup();
            Assert.hasText(nameServer, "[rocketmq.name-server] must not be null");
            Assert.hasText(groupName, "[rocketmq.producer.group] must not be null");

            String accessChannel = rocketMQProperties.getAccessChannel();

            DefaultMQProducer producer;
            String ak = rocketMQProperties.getProducer().getAccessKey();
            String sk = rocketMQProperties.getProducer().getSecretKey();
            if (!StringUtils.isEmpty(ak) && !StringUtils.isEmpty(sk)) {
                producer = new DefaultMQProducer(groupName, new AclClientRPCHook(new SessionCredentials(ak, sk)),
                        rocketMQProperties.getProducer().isEnableMsgTrace(),
                        rocketMQProperties.getProducer().getCustomizedTraceTopic());
                producer.setVipChannelEnabled(false);
            } else {
                producer = new DefaultMQProducer(groupName, rocketMQProperties.getProducer().isEnableMsgTrace(),
                        rocketMQProperties.getProducer().getCustomizedTraceTopic());
            }

            producer.setNamesrvAddr(nameServer);
            if (!StringUtils.isEmpty(accessChannel)) {
                producer.setAccessChannel(AccessChannel.valueOf(accessChannel));
            }
            producer.setSendMsgTimeout(producerConfig.getSendMessageTimeout());
            producer.setRetryTimesWhenSendFailed(producerConfig.getRetryTimesWhenSendFailed());
            producer.setRetryTimesWhenSendAsyncFailed(producerConfig.getRetryTimesWhenSendAsyncFailed());
            producer.setMaxMessageSize(producerConfig.getMaxMessageSize());
            producer.setCompressMsgBodyOverHowmuch(producerConfig.getCompressMessageBodyThreshold());
            producer.setRetryAnotherBrokerWhenNotStoreOK(producerConfig.isRetryNextServer());

            return new DefaultProducerAdapter(producer, serializer);
        }
    }

//    @Profile(PROFILE_ALIYUN)
    @ConditionalOnClass(MQAdmin.class)
    @ConditionalOnProperty(prefix = "rocketmq", value = {"name-server", "producer.group"})
    @Import({AliyunListenerContainerConfiguration.class})
    public class AliyunConfiguration {

        @Autowired
        private MessageSerializer serializer;


        @Bean(initMethod = "start", destroyMethod = "stop")
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

            String appName = RocketMQAutoConfiguration.this.environment.resolvePlaceholders("${spring.application.name}");
            properties.setProperty(PropertyKeyConst.InstanceName, appName + PREFIX + "defaultMQProducer");

            properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, String.valueOf(producerConfig.getSendMessageTimeout()));

            ProducerBean producerBean = new ProducerBean();
            producerBean.setProperties(properties);

            return new AliProducerAdapter(producerBean, serializer);
        }
    }


    /**
     * Consumer Proxy
     */
    class AliyunListenerContainerConfiguration extends AbstractListenerContainerConfiguration {

        public AliyunListenerContainerConfiguration(MessageSerializer serializer,
                                                    StandardEnvironment environment,
                                                    RocketMQProperties rocketMQProperties) {
            super(serializer, environment, rocketMQProperties);
        }

        @Override
        Class<? extends AbstractMQListenerContainer> getContainerClazz() {
            return AliRocketMQListenerContainer.class;
        }

    }

    class DefaultListenerContainerConfiguration extends AbstractListenerContainerConfiguration {

        public DefaultListenerContainerConfiguration(
                MessageSerializer serializer,
                StandardEnvironment environment,
                RocketMQProperties rocketMQProperties) {
            super(serializer, environment, rocketMQProperties);
        }

        @Override
        Class<? extends AbstractMQListenerContainer> getContainerClazz() {
            return DefaultRocketMQListenerContainer.class;
        }

    }

}

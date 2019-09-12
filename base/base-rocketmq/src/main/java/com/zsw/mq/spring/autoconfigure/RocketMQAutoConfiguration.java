package com.zsw.mq.spring.autoconfigure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zsw.mq.spring.api.Producer;
import com.zsw.mq.spring.config.RocketMQConfigUtils;
import com.zsw.mq.spring.core.RocketMQTemplate;
import com.zsw.mq.spring.serializer.JacksonMessageSerializer;
import com.zsw.mq.spring.serializer.MessageSerializer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author ZhangShaowei on 2019/9/12 11:19
 **/
@Configuration
public class RocketMQAutoConfiguration {


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
    public MessageSerializer messageSerializer(ObjectProvider<ObjectMapper> objectMapper) {
        return new JacksonMessageSerializer(objectMapper.getIfAvailable(this::objectMapper));
    }


    private ObjectMapper objectMapper() {
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

}

package com.zsw.mq.spring.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsw.mq.spring.api.Producer;
import com.zsw.mq.spring.config.RocketMQConfigUtils;
import com.zsw.mq.spring.core.RocketMQTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}

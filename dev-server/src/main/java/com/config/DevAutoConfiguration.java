package com.config;

import com.zsw.metrics.micrometer.healthindicator.mq.MqTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration
 *
 * @author ZhangShaowei on 2018/4/12 11:09
 **/
@Configuration
public class DevAutoConfiguration {


    @Bean("rocketMqTemplate")
    public MqTemplate mqTemplate1() {
        return () -> "1";
    }

}

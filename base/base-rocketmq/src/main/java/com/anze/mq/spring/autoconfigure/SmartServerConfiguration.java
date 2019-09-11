package com.anze.mq.spring.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 在这里做适配
 *
 * @author ZhangShaowei on 2019/9/11 16:17
 **/
@Configuration
public class SmartServerConfiguration {

    @Profile("secure")
    @Configuration
    public static class DefaultRocketMqConfiguration {




    }

}

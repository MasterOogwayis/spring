package com.zsw.base.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2020/9/14 17:41
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerMakerConfiguration {

    @Bean
    public Maker maker() {
        return new Maker();
    }

    static class Maker {

    }

}

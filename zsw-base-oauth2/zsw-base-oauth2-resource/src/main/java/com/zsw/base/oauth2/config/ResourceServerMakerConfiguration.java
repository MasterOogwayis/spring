package com.zsw.base.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2020/9/14 17:38
 */
@Configuration(proxyBeanMethods = false)
public class ResourceServerMakerConfiguration {

    @Bean
    public Marker zuulServerMarkerBean() {
        return new Marker();
    }

    class Marker {

    }

}

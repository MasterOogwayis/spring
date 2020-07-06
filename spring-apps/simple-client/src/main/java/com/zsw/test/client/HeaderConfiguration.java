package com.zsw.test.client;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @author ZhangShaowei on 2020/5/14 17:06
 */
public class HeaderConfiguration {

    @Bean
    public RequestInterceptor headerInterceptor() {
        return template -> {
            template.header("attr", "This is a test.");
        };
    }


}

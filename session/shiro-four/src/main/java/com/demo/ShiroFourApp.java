package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author ZhangShaowei on 2021/7/26 11:11
 */
@SpringBootApplication(proxyBeanMethods = false)
public class ShiroFourApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(ShiroFourApp.class, args);
    }

    @Bean
    public DefaultCookieSerializerCustomizer cookieSerializerCustomizer() {
        return cookieSerializer -> cookieSerializer.setUseBase64Encoding(Boolean.FALSE);
    }

}

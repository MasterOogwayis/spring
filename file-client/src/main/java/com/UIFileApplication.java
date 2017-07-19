package com;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import javax.servlet.MultipartConfigElement;

/**
 * @author ZhangShaowei on 2017/4/26 15:22
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UIFileApplication {


    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(UIFileApplication.class, args);
    }


    @Bean
    MultipartConfigElement createMultipartConfigElement() {
        MultipartConfigFactory mcf = new MultipartConfigFactory();
        /**
         * 设置最大上传文件的大小，默认是1MB
         */
        mcf.setMaxFileSize("50MB");
        return mcf.createMultipartConfig();
    }


    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }

    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }

}

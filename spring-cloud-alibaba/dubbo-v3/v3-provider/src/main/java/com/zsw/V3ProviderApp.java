package com.zsw;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2022/1/5 11:03
 */
@EnableDubbo
@SpringBootApplication
public class V3ProviderApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(V3ProviderApp.class, args);
    }

}

package com.zsw;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangShaowei on 2020/8/31 11:22
 */
@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

}

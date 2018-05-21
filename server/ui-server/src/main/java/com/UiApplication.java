package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * UiApplication
 *
 * @author ZhangShaowei on 2018/5/18 14:22
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class UiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }

}

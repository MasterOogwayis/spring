package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangShaowei on 2020/8/31 13:47
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(ProviderApp.class, args);
    }

}

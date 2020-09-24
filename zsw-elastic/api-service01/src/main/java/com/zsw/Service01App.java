package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Shaowei Zhang on 2020/9/20 15:11
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Service01App {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(Service01App.class, args);
    }

}

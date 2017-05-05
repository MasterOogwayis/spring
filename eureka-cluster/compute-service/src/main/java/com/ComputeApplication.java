package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangShaowei on 2017/5/3 10:14
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ComputeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputeApplication.class, args);
    }

}

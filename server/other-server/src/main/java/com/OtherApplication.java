package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangShaowei on 2017/11/9 9:42
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class OtherApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class, args);
    }

}

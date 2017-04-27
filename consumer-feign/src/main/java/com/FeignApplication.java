package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author ZhangShaowei on 2017/4/26 15:22
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignApplication {


    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }

}

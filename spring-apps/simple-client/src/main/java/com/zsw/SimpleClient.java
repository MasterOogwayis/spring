package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZhangShaowei on 2020/5/11 14:27
 */
@EnableHystrix
@EnableFeignClients
@SpringCloudApplication
public class SimpleClient {

    /**
     * start server
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(SimpleClient.class, args);
    }


}

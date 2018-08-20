package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ZhangShaowei on 2018/8/20 14:06
 **/
@SpringBootApplication
@EnableEurekaClient
public class TestServer {

    public static void main(String[] args) {
        SpringApplication.run(TestServer.class, args);
    }

}

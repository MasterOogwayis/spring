package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ZhangShaowei on 2017/9/8 13:22
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterApplication {

    /**
     * main class
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class, args);
    }

}

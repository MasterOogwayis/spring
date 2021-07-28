package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2021/7/26 11:11
 */
@SpringBootApplication(proxyBeanMethods = false)
public class ShiroThreeApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(ShiroThreeApp.class, args);
    }

}

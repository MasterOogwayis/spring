package com.zsw.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2021/5/10 16:09
 */
@SpringBootApplication
public class StandaloneApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(StandaloneApp.class, args);
    }

}

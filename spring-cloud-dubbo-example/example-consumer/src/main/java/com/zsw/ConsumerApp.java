package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2020/8/31 11:22
 */
@SpringBootApplication
public class ConsumerApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

}

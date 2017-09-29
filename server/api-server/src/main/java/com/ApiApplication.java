package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZhangShaowei on 2017/9/15 13:42
 */
@SpringBootApplication
public class ApiApplication {

    /**
     * main
     *
     * @param args args
     */
    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ApiApplication.class, args);
    }

}

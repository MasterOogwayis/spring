package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2021/12/21 15:11
 */
@SpringBootApplication(scanBasePackages = {"com.demo", "com.zsw"})
public class SecurityDemoApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApp.class, args);
    }

}

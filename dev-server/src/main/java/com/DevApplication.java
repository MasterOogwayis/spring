package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a dev application.
 *
 * @author ZhangShaowei on 2018/3/12 11:04
 **/
@SpringBootApplication
//@EnableDiscoveryClient
public class DevApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DevApplication.class, args);
    }

}

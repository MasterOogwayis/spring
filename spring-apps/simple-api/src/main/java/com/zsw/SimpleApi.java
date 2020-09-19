package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2020/5/11 14:15
 */
//@EnableDiscoveryClient
@SpringBootApplication
public class SimpleApi {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(SimpleApi.class, args);
    }


}

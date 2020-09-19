package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2020/7/8 9:09
 */
//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication
public class RunTests {


    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(RunTests.class, args);
    }


}

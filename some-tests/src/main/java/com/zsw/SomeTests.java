package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ZhangShaowei on 2022/2/17 10:31
 */
@SpringBootApplication
public class SomeTests {


    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SomeTests.class, args);
    }

}

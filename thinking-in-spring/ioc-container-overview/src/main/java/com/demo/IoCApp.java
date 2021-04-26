package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2021/4/25 11:41
 */
@Slf4j
@SpringBootApplication
public class IoCApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(IoCApp.class, args);
        log.info("Hello World");
        log.debug("Hello World");
        log.warn("Hello World");
        log.error("Hello World");
        log.trace("Hello World");
    }

}

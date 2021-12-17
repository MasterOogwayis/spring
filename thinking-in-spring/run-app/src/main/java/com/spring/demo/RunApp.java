package com.spring.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

/**
 * @author ZhangShaowei on 2021/12/17 9:25
 */
@Slf4j
@SpringBootApplication
public class RunApp {


    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
    }


    @EventListener(ContextClosedEvent.class)
    public void onClose(ContextClosedEvent event) {
        log.info("close event, Thread name: {}", Thread.currentThread().getName());
    }

}

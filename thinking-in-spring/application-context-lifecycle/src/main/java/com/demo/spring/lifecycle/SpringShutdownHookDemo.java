package com.demo.spring.lifecycle;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author ZhangShaowei on 2021/12/17 9:10
 */
@Slf4j
public class SpringShutdownHookDemo {


    @SneakyThrows
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> log.info("线程: {}", Thread.currentThread().getName()));

        applicationContext.refresh();
        applicationContext.registerShutdownHook();


        System.in.read();

        applicationContext.close();


    }


}

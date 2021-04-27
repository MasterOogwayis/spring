package com.demo.spring.beans.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;

import java.util.Objects;

/**
 * @author ZhangShaowei on 2021/4/26 14:10
 */
@Getter
@Setter
@ToString
public class UserService implements SmartLifecycle {

    private boolean started = false;

    public UserService(String name) {
        this.name = name;
    }

    private String name;

    private Integer age;

    private String address;


    public void init() {
        System.out.println("----------------------- call init -----------------------");
    }

    @Override
    public void start() {
        started = true;
        System.out.println("start ...");
    }

    @Override
    public void stop() {
        started = false;
        System.out.println("stop ...");
    }

    @Override
    public boolean isRunning() {
        return this.started;
    }
}

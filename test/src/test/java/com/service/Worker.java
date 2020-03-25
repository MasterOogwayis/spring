package com.service;

/**
 * @author ZhangShaowei on 2020/3/24 9:52
 */
public interface Worker {

    default void doJob(String name){};

    void test(String message);

}

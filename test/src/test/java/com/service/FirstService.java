package com.service;

/**
 * @author ZhangShaowei on 2020/3/25 10:32
 */
public class FirstService extends BaseService<String> implements Worker {

    public void doSomething(String message) {
        super.print(message);
    }

    @Override
    public void test(String message) {

    }
}

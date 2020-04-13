package com.service;

/**
 * @author ZhangShaowei on 2020/3/25 10:32
 */
public class BaseService<T> {

    public String print(T message) {
        System.out.println(message);
        return "";
    }

}

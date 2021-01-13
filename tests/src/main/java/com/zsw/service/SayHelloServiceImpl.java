package com.zsw.service;

import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2021/1/13 13:00
 */
@Service
public class SayHelloServiceImpl implements ISayHelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}

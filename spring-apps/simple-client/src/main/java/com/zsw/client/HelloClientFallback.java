package com.zsw.client;

import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2020/5/11 14:29
 */
@Component
public class HelloClientFallback implements HelloClient {
    @Override
    public String hello(String name) {
        return "faild " + name;
    }
}

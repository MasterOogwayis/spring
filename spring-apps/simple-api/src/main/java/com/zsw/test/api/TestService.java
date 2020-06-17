package com.zsw.test.api;

import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2020/6/1 20:59
 */
@Service
public class TestService {

    @Log
    public String hello(String name) {
        String str = "Hello " + name;
        System.out.println(str);
        return str;
    }


}

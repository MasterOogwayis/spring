package com.zsw.service;

import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author ZhangShaowei on 2022/1/5 11:02
 */
@DubboService(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

}

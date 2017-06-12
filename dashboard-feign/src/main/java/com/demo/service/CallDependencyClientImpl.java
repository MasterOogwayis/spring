package com.demo.service;

import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2017/6/1 15:27
 */
@Component
public class CallDependencyClientImpl implements CallDependencyClient {
    @Override
    public Integer add(Integer a, Integer b) {
        return 0;
    }
}

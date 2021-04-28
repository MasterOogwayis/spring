package com.demo.spring.dependency.domain;

import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/4/28 15:51
 */
@Component
public class StringHolder implements ObjectHolder<String> {
    @Override
    public String getObject() {
        return "Hello World";
    }
}

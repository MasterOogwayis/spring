package com.zsw.aop.i;

import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/9/17 17:10
 */
//@Component
public class A implements WorkService {
    @Override
    public Object doSomething() {
        return "A";
    }
}

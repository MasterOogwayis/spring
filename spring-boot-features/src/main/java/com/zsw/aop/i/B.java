package com.zsw.aop.i;

/**
 * @author ZhangShaowei on 2021/9/17 17:10
 */
//@Component
public class B implements WorkService {
    @Override
    public Object doSomething() {
        return "B";
    }
}

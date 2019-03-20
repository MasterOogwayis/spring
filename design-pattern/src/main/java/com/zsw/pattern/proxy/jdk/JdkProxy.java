package com.zsw.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ZhangShaowei on 2019/3/20 9:33
 **/
public class JdkProxy implements InvocationHandler {

    private Object doSomething;

    @SuppressWarnings("unchecked")
    public <T> T getInstance(T t) {
        this.doSomething = t;
        Class<?> clazz = t.getClass();
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk proxy start ...");
        Object invoke = method.invoke(doSomething, args);
        System.out.println("jdk proxy end ...");
        return invoke;
    }
}

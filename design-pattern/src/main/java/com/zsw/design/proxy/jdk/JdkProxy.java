package com.zsw.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk 提供的动态代理，通过实现接口的方式代理。所以必须有接口
 *
 * @author Shaowei Zhang on 2019/3/11 19:08
 **/
public class JdkProxy implements InvocationHandler {

    private Object target;

    @SuppressWarnings("unchecked")
    public <T> T getInstance(T target) {
        this.target = target;
        Class clazz = target.getClass();
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk proxy...start");
        Object invoke = method.invoke(this.target, args);
        System.out.println("jdk proxy...end");
        return invoke;
    }
}

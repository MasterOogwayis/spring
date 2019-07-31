package com.zsw.pattern.proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ZhangShaowei on 2019/3/20 9:55
 **/
public class CglibProxy implements MethodInterceptor {

    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib proxy start ...");
        Object invokeSuper = proxy.invokeSuper(obj, args);
        System.out.println("cglib proxy end ...");
        return invokeSuper;
    }

}

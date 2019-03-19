package com.zsw.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 通过继承的方式实现代理 可以不需要接口，spring 使用的代理方式
 *
 * @author Shaowei Zhang on 2019/3/11 19:30
 **/
public class CglibProxy implements MethodInterceptor {


    /**
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib proxy...start");
        Object object = proxy.invokeSuper(obj, args);
        System.out.println("cglib proxy...end");
        return object;
    }
}

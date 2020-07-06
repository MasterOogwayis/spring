package com.zsw.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ZhangShaowei on 2020/5/26 14:19
 */
@Slf4j
public class JdkProxy implements InvocationHandler {

    private Object target;

    public <T> T getInstance(T target) {
        this.target = target;
        Class<?> clazz = target.getClass();
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(target, args);
        }
        log.info("before: {}", method.getName());
        Object invoke;
        if (method.isDefault()) {
            invoke = BeanUtils.instantiateClass(
                    MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE), target.getClass(), 15)
                    .in(method.getDeclaringClass())
                    .unreflectSpecial(method, method.getDeclaringClass())
                    .bindTo(proxy)
                    .invokeWithArguments(args);
        } else {
            invoke = method.invoke(target, args);
        }
//        Object invoke = method.invoke(this.target, args);
        log.info("invoke method = {}, result = {}", method.getName(), invoke);
        return invoke;
    }
}

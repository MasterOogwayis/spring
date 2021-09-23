package com.zsw.proxy;

import com.zsw.utils.MethodHandlesUtils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangShaowei on 2020/5/26 14:19
 */
public class JdkProxy implements InvocationHandler {

    private Object target;

    private MethodHandles.Lookup lookup;

    private ConcurrentHashMap<Method, MethodHandle> handles = new ConcurrentHashMap<>();

    public <T> T getInstance(T target) {
        this.target = target;
        Class<?> clazz = target.getClass();
        this.lookup = MethodHandlesUtils.lookup(clazz);
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(target, args);
        }
        return handles.computeIfAbsent(method, m -> {
            try {
                return lookup.unreflect(method);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).bindTo(proxy).invokeWithArguments(args);
//        log.info("before: {}", method.getName());
//        Object invoke;
//        if (method.isDefault()) {
//            invoke = BeanUtils.instantiateClass(
//                    MethodHandles.Lookup.class.getDeclaredConstPructor(Class.class, Integer.TYPE), target.getClass(), 15)
//                    .in(method.getDeclaringClass())
//                    .unreflectSpecial(method, method.getDeclaringClass())
//                    .bindTo(proxy)
//                    .invokeWithArguments(args);
//        } else {
//            invoke = method.invoke(target, args);
//        }
////        Object invoke = method.invoke(this.target, args);
//        log.info("invoke method = {}, result = {}", method.getName(), invoke);
    }
}

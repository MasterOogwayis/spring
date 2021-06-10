package com.zsw.utils;

/**
 * @author ZhangShaowei on 2021/6/8 15:06
 */
public class OnceThreadLocal<T> extends ThreadLocal<T> {

    @Override
    public T get() {
        T obj = super.get();
        super.remove();
        return obj;
    }
}

package com.zsw.base.redis.annotation;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * @author ZhangShaowei on 2017/12/6 9:45
 */

public class LockKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return null;
    }
}

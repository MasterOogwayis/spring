package com.zsw.base.cache.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author ZhangShaowei on 2017/10/12 10:09
 */

public class CustomCacheMethodIterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println(111);
        return null;
    }
}

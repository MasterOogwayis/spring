package com.zsw.proxy;

import com.zsw.annotation.Wrapped;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author ZhangShaowei on 2021/9/23 14:08
 */
public class SupportThis implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (invocation.getMethod().isAnnotationPresent(Wrapped.class)) {
            System.out.println(111);
        }
        return invocation.proceed();
    }
}

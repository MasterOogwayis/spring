package com.zsw.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shaowei Zhang on 2022/3/7 20:33
 */
@Slf4j
public class HelloMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Hello {}", invocation.getMethod().getName());
        return invocation.proceed();
    }
}

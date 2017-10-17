package com.zsw.base.aspect;

import com.zsw.base.cache.annotation.CacheLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ZhangShaowei on 2017/10/12 16:13
 */
//@Aspect
//@Order(1)
//@Component
public class CacheLockAspect {

    @Pointcut("@annotation(com.zsw.base.cache.annotation.CacheLock)")
    public void methods() {
    }


    /**
     * @return
     */
    @Around("methods()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        CacheLock cacheLock = targetMethod.getAnnotation(CacheLock.class);
        System.err.println(cacheLock.key());
        return joinPoint.proceed(joinPoint.getArgs());

    }

}

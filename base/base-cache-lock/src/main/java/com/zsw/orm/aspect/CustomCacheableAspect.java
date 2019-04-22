package com.zsw.orm.aspect;

import com.zsw.orm.cache.annotation.CustomCacheable;
import com.zsw.orm.utils.KeyGeneratorUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author ZhangShaowei on 2017/10/12 16:13
 */
//@Aspect
//@Order(1)
//@Component
public class CustomCacheableAspect {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.zsw.orm.cache.annotation.CustomCacheable)")
    public void methods() {
    }


    /**
     * @param joinPoint jp
     * @return Object
     * @throws Throwable
     */
    @Around("methods()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        CustomCacheable customCacheable = targetMethod.getAnnotation(CustomCacheable.class);
        String key = KeyGeneratorUtils.parseKey(customCacheable.key(), targetMethod, joinPoint.getArgs());
        this.logger.debug("AOP:" + key);
        return joinPoint.proceed(joinPoint.getArgs());

    }


}

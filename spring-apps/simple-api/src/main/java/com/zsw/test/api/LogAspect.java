package com.zsw.test.api;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Administrator on 2019/9/22 17:02
 **/
@Slf4j
@Aspect
@Order(1)
@Component
public class LogAspect {


    @Pointcut("@annotation(com.zsw.test.api.Log)")
    public void methods() {
    }

    @Before("methods()")
    public void before() {

    }

    @SneakyThrows
    @Around("methods()")
    public Object timeout(ProceedingJoinPoint pjp) {
        Object returnValue = pjp.proceed(pjp.getArgs());
        log.info("Aop confirm ...");
        return returnValue;
    }


}

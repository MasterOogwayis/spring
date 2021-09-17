package com.zsw.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/9/17 17:05
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class IAspect {


    @Pointcut("execution(* com.zsw.aop.WorkService.do*(..))")
    public void methods() {
        // pointcut
    }

    @Around("methods()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("before -> param = {}", joinPoint.getArgs());
        Object result = joinPoint.proceed(joinPoint.getArgs());
        log.info("after -> result = {}", result);
        return result;
    }


}

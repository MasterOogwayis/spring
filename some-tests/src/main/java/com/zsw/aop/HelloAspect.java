package com.zsw.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author Shaowei Zhang on 2022/3/7 21:16
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class HelloAspect {

    @Pointcut("@annotation(com.zsw.aop.AHello)")
    public void pointcut() {
        // Do nothing
    }

    @Around("pointcut()")
    public Object timeout(ProceedingJoinPoint pjp) throws Throwable {
        log.info("AHello ...");
        return pjp.proceed();
    }

}

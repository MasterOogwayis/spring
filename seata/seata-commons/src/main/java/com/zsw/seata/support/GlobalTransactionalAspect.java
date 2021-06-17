package com.zsw.seata.support;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author ZhangShaowei on 2017/10/12 16:13
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class GlobalTransactionalAspect {


    /**
     * 切入点
     */
    @Pointcut("@annotation(com.zsw.seata.support.UnbindGlobalTransactional)")
    public void methods() {
    }


    /**
     * @param joinPoint ProceedingJoinPoint
     * @return method.invoke()
     * @throws Throwable
     */
    @Around("methods()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        String xid = RootContext.unbind();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            if (StringUtils.hasText(xid)) {
                RootContext.bind(xid);
            }
        }
    }


}

package com.zsw.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Shaowei Zhang on 2022/3/16 20:04
 */
@Component
@Slf4j
public class HelloAdvisor implements PointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        return new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                return clazz -> clazz.getPackageName().startsWith("com.zsw");
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new StaticMethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return method.isAnnotationPresent(EchoHello.class);
                    }
                };
            }
        };
    }

    @Override
    public Advice getAdvice() {
        return (MethodInterceptor) invocation -> {
            Object rel = invocation.proceed();
            log.info("Hello {}", invocation.getMethod().getName());
            return rel;
        };
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}

package com.zsw.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Shaowei Zhang on 2022/3/20 19:58
 */
@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Component
public class LoggingAdvisor implements PointcutAdvisor {
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
                        return method.isAnnotationPresent(Logging.class);
                    }
                };
            }
        };
    }

    @Override
    public Advice getAdvice() {
        return (MethodInterceptor) invocation -> {
            log.info("call method: {}", invocation.getMethod().getName());
            return invocation.proceed();
        };
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}

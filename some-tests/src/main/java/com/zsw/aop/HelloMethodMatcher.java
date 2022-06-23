package com.zsw.aop;

import org.springframework.aop.MethodMatcher;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Method;

/**
 * @author Shaowei Zhang on 2022/3/7 20:31
 */
public class HelloMethodMatcher implements MethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        return mostSpecificMethod.isAnnotationPresent(Hello.class);
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return false;
    }
}

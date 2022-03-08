package com.zsw.aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

/**
 * @author Shaowei Zhang on 2022/3/7 20:32
 */
public class HelloPointCut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
//        return clazz -> clazz.getPackageName().startsWith("com.zsw");
        return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new HelloMethodMatcher();
    }
}

package com.zsw.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.stereotype.Component;

/**
 * @author Shaowei Zhang on 2022/3/7 20:35
 */
//@Role(ROLE_INFRASTRUCTURE)
@Component
public class HelloPointcutAdvisor implements PointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        return new HelloPointCut();
    }

    @Override
    public Advice getAdvice() {
        return new HelloMethodInterceptor();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}

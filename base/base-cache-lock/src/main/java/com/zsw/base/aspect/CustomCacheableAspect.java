package com.zsw.base.aspect;

import com.zsw.base.cache.annotation.CustomCacheable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ZhangShaowei on 2017/10/12 16:13
 */
//@Aspect
//@Order(1)
//@Component
public class CustomCacheableAspect {

    @Pointcut("@annotation(com.zsw.base.cache.annotation.CustomCacheable)")
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
        String key = parseKey(customCacheable.key(), targetMethod, joinPoint.getArgs());
        System.err.println(customCacheable.key());
        System.out.println(key);
        return joinPoint.proceed(joinPoint.getArgs());

    }

    /**
     * 获取缓存的key
     * key 定义在注解上，支持SPEL表达式
     *
     * @param key
     * @param method
     * @param args
     * @return
     */
    private String parseKey(String key, Method method, Object[] args) {


        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

}

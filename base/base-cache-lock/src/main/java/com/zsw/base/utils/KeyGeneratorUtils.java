package com.zsw.base.utils;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @author ZhangShaowei on 2018/1/12 14:32
 */

public class KeyGeneratorUtils {


    /**
     * 获取被拦截方法参数名列表(使用Spring支持类库)
     */
    private static final LocalVariableTableParameterNameDiscoverer LVTPND
            = new LocalVariableTableParameterNameDiscoverer();

    /**
     * 获取缓存的key
     * key 定义在注解上，SPEL表达式
     *
     * @param key    SPEL表达式
     * @param method method
     * @param args   args
     * @return
     */
    public static String parseKey(final String key, final Method method, final Object[] args) {

        String[] paraNameArr = LVTPND.getParameterNames(method);

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

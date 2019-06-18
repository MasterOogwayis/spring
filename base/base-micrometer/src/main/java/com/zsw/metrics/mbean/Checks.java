package com.zsw.metrics.mbean;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2019/6/13 17:07
 **/
public class Checks {


    public static void hasText(String expression, String s) {
        if (!StringUtils.hasText(expression)) {
            throw new RemoteCallException(s);
        }
    }

    public static void isTrue(boolean b, String s) {
        if (!b) {
            throw new RemoteCallException(s);
        }
    }

    public static void notNull(Object clazz, String s) {
        if (Objects.isNull(clazz)) {
            throw new RemoteCallException(s);
        }

    }

    public static void isNull(Object foundMethod, String s) {
        if (Objects.nonNull(foundMethod)) {
            throw new RemoteCallException(s);
        }
    }

    public static void notEmpty(Collection<?> mayMethods, String s) {
        if (CollectionUtils.isEmpty(mayMethods)) {
            throw new RemoteCallException(s);
        }
    }
}

package com.demo;

import org.springframework.core.GenericTypeResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangShaowei on 2021/8/4 10:03
 */
public class GenericTypeResolverTests {

    public static void main(String[] args) {
        Class<?> aType = GenericTypeResolver.resolveTypeArgument(Z.class, A.class);
        // String
        System.out.println(aType);

        Class<?> bClass = GenericTypeResolver.resolveTypeArgument(Z.class, B.class);
        // Integer
        System.out.println(bClass);

        Class<?> cClass = GenericTypeResolver.resolveTypeArgument(Z.class, C.class);
        // Long
        System.out.println(cClass);
        Class<?>[] dClass = GenericTypeResolver.resolveTypeArguments(Z.class, D.class);

        // List, Map
        System.out.println(Arrays.toString(dClass));

    }


    static class Z implements A<String>, B<Integer>, D<List<?>, Map<?, ?>> {

    }

    interface A<T> {

    }

    interface B<T> extends C<Long> {

    }

    interface C<T> {

    }

    interface D<T, R> {

    }

}

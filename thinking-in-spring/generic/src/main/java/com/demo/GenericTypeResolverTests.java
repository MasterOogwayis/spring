package com.demo;

import lombok.SneakyThrows;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangShaowei on 2021/8/4 10:03
 */
public class GenericTypeResolverTests {

    public static void main(String[] args) {
        test2();

    }

    @SneakyThrows
    private static void test2() {
        Method get = GenericTypeResolverTests.class.getMethod("get");
        Class<?> returnType = GenericTypeResolver.resolveReturnType(get, GenericTypeResolverTests.class);

        // Z
        System.out.println(returnType);

        Class<?> bTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(get, B.class);
        // Integer
        System.out.println(bTypeArgument);

        Class<?> orderedTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(get, Ordered.class);
        // null -> 一般类型不具备泛型参数
        System.out.println(orderedTypeArgument);
    }


    private static void test1() {
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

    public static Z get() {
        return null;
    }


    static class Z implements A<String>, B<Integer>, D<List<?>, Map<?, ?>>, Ordered {

        @Override
        public int getOrder() {
            return 0;
        }
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

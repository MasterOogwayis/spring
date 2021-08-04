package com.demo;

import lombok.SneakyThrows;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/4 9:50
 */
public class ResolvableTypeTests {

    @SneakyThrows
    public static void main(String[] args) {
        Method getList = ResolvableTypeTests.class.getMethod("getList");
        Class<?> returnType = GenericTypeResolver.resolveReturnType(getList, ResolvableTypeTests.class);
        // StringList
        System.out.println(returnType);

        Class<?> clazz = GenericTypeResolver.resolveReturnTypeArgument(getList, List.class);
        // StringList
        System.out.println(clazz);

        Class<?> aClass = GenericTypeResolver.resolveTypeArgument(returnType, List.class);
        // StringList
        System.out.println(aClass);


    }


    public static StringList getList() {
        return null;
    }

}

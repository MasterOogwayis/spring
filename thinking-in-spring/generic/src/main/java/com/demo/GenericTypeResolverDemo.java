package com.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangShaowei on 2021/8/3 14:54
 */
@Slf4j
public class GenericTypeResolverDemo {

    @SneakyThrows
    public static void main(String[] args) {

        // String 是 Comparable<String> 具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");

        // ArrayList<Object> 是 List 泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");

        // StringList 也是 List 泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

        // 具备 ParameterizedType 返回，否则 null

        // TypeVariable
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);

    }


    public static void displayReturnTypeGenericInfo(
            Class<?> containingClass, Class<?> genericIfc,
            String methodName, Class... argumentTypes) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName, argumentTypes);

        // 常规类作为方法返回值
        Class<?> clazz = GenericTypeResolver.resolveReturnType(method, containingClass);
        log.info("GenericTypeResolver.resolveReturnType({}, {}) = {}", methodName, containingClass.getSimpleName(), clazz);

        // 常规类型不具备泛型参数
        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        log.info("GenericTypeResolver.resolveReturnTypeArgument({}, {})={}", methodName, containingClass.getSimpleName(), returnTypeArgument);

    }

    public static String getString() {
        return null;
    }

    public static StringList getStringList() {
        return null;
    }

    public static ArrayList<Object> getList() { // 泛型参数类型具体化
        return null;
    }

}

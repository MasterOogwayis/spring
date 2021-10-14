package com.demo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2021/8/3 9:18
 */
public class GenericApiDemo {

    public static void main(String[] args) {

        // 原生类型 primitive types: int long float
        Class intClass = int.class;

        // 数组类型 array types: int[], Object[]
        Class objectArrayClass = Object[].class;

        // 原始类型 row types: java.lang.String
        Class rawClass = String.class;

        // 泛型参数类型 parameterized type
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();

        // 泛型类型变量 Type Variable
        System.out.println(parameterizedType.toString());

        // <E>
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

        Stream.of(actualTypeArguments)
                .map(TypeVariable.class::cast)
                .forEach(System.out::println);


    }

}

package com.demo;

import java.lang.reflect.Method;

/**
 * @author ZhangShaowei on 2021/8/3 14:54
 */
public class GenericTypeResolverDemo {

    public static void main(String[] args) {

    }



    public static void displayReturnTypeGenericInfo(
            Class<?> containingClass, Class<?> genericIfc,
            String methodName, Class ... argumentTypes) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName, argumentTypes);


    }

}

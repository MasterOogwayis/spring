package com.demo;

import com.demo.bridge.Earth;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ZhangShaowei on 2021/6/18 17:03
 */
public class MainTests {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz = String.class;
        Object o = clazz.newInstance();
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.newInstance();
    }

}

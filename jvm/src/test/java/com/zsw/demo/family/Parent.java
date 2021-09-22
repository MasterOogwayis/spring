package com.zsw.demo.family;

import com.zsw.utils.LookupSupport;

import java.lang.invoke.MethodHandles;

/**
 * @author ZhangShaowei on 2021/9/22 11:07
 */
public class Parent implements Grad {

    public static void hello() {
        System.out.println("Hello World!");
    }

    @Override
    public void say() {
        System.out.println("parent ...");
    }

    public static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }

}

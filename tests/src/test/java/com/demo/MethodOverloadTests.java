package com.demo;

/**
 * @author ZhangShaowei on 2021/9/2 13:59
 */
public class MethodOverloadTests {

    public static void main(String[] args) {
        invoke(null, 1);
        invoke(null, 1, 2);
        invoke(null, new Object[]{1});
    }


    private static void invoke(Object obj, Object... args) {
        System.out.println("method 0");
    }

    private static void invoke(String str, Object obj, Object... args) {
        System.out.println("method 1");
    }

}

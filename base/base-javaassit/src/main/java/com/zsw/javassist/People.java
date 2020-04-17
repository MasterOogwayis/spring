package com.zsw.javassist;

/**
 * @author ZhangShaowei on 2020/4/15 14:58
 */
public class People extends Parent {

    static {
        System.out.println("People init ...");
    }


    public static String str = "";

    public static void println(String str) {
        System.out.println(str);
    }


}

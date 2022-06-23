package com.tests;

import java.util.ArrayList;

/**
 * @author Shaowei Zhang on 2022/3/17 20:48
 */
public class Test1 {


    public static void main(String[] args) {
        System.out.println(hello());

    }

    public static int hello() {
        A a = new A();
        a.i = 1;
        return a.i;
    }


    static class A {
        int i;
    }


}

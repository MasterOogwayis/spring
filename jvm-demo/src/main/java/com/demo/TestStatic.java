package com.demo;

/**
 * @author Shaowei Zhang on 2019/3/12 22:42
 **/
public class TestStatic {

    static int i = 0;

    static {
        i = 1;
        System.out.println(i);
        if (true) {
            System.out.println(1);
        }

    }


}

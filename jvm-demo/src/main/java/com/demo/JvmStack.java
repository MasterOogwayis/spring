package com.demo;

import java.io.Serializable;

/**
 * 虚拟机栈 demo
 *
 * @author ZhangShaowei on 2019/3/14 13:31
 **/
public class JvmStack {

    private int i = 0;

    private static int s;

    private static final String str = "str";

    public void run() {
        int j = i;
        {
            int m = j + 1;
        }
        test();
        long n = 1L;
        return;
    }

    public String test() {
        String str = "message";
        return str;
    }


    public static void main(String[] args) {
        int[][][] arrays = new int[0][1][-1];
        {
            byte[] bytes = new byte[64 * 1024 * 1024];
        }
        int i = 0;
        System.out.println(s);
        System.gc();
    }

    @FunctionalInterface
    static interface Marketer {

        String getName();

    }


}

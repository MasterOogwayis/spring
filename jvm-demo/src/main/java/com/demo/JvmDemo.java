package com.demo;

/**
 * @author ZhangShaowei on 2019/7/25 10:48
 **/
public class JvmDemo {

    private String str = "a";

    private int i = 0;

    public String test(int j) {
        int num = i + j;
        String s = str + num;
        return s;
    }


}

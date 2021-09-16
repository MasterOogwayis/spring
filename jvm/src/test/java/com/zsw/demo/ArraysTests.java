package com.zsw.demo;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author ZhangShaowei on 2021/9/15 14:02
 */
public class ArraysTests {

    public static void main(String[] args) {
        long[] o = (long[]) Array.newInstance(long.class, 10);
        System.out.println(Arrays.toString(o));
    }

}

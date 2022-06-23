package com;

import java.util.ArrayList;

/**
 * @author Shaowei Zhang on 2022/3/8 11:15
 */
public class ReflectionTests {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws ClassNotFoundException {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];  // 出现一次Minor GC


        System.gc();
    }

}

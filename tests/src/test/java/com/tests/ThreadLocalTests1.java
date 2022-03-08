package com.tests;

/**
 * @author Shaowei Zhang on 2022/3/7 0:11
 */
public class ThreadLocalTests1 {

    private static final ThreadLocal<String> CACHE = new ThreadLocal<>();

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            CACHE.set("Hello World");
        }

    }

}

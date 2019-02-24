package com;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Shaowei Zhang on 2019/2/20 14:28
 **/
public class HelloWorldDemo {


    /**
     * 常量、静态变量
     */
    private final int i = 0;

    /**
     *
     */
    private static final int k = 0; // k = 10

    /**
     * 成员变量
     */
    private Object object = new Object();
    private int sss = 0;


    public void methodOne(int i) {
        int j = 0;
        int sum = i + j;
        Object obj = object;
        long start = System.currentTimeMillis();

        methodTwo();

        return;

    }

    public void methodTwo() {

        Path path = Paths.get("data", "images", "1.img");
        File file = path.toFile();

    }


    public static void main(String[] args) {

    }


}

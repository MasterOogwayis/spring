package com.demo;

/**
 * @author Shaowei Zhang on 2019/3/3 20:52
 **/
public class TestVolatile {

    private volatile static int i = 0;


    public static void main(String[] args) {

        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
                i++;
            }).start();

        }


    }

}

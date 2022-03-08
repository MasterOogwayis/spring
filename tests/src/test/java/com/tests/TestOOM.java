package com.tests;

/**
 * @author Shaowei Zhang on 2022/3/7 15:58
 */
public class TestOOM {

    public static void main(String[] args) {
        try {
             byte[] data =  new byte[1024 * 1024 * 64];
        } catch (Error e) {
            e.printStackTrace();
        }

        System.out.println("Hello World!");
    }

    public static void t() {

    }

}

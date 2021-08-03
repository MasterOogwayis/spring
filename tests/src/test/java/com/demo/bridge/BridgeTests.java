package com.demo.bridge;

/**
 * @author ZhangShaowei on 2021/8/3 13:30
 */
public class BridgeTests {

    public static void main(String[] args) {
        System.out.println(new Earth().getName());
        System.err.println(new Mars().getName());
    }

}

package com.demo.bridge;

/**
 * @author ZhangShaowei on 2021/8/3 13:29
 */
public class Mars implements Planet {
    @Override
    public StringBuilder getName() {
        return new StringBuilder("Name").append(" is ").append(" 火星");
    }
}

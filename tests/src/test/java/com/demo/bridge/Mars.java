package com.demo.bridge;

/**
 * @author ZhangShaowei on 2021/6/18 17:02
 */
public class Mars implements Planet {
    @Override
    public StringBuilder getName() {
        return new StringBuilder("Mars");
    }
}

package com.demo.ex;

/**
 * @author ZhangShaowei on 2021/10/13 11:20
 */
public interface B {

    default void hello() {
        System.out.println("I`m B");
    }

}

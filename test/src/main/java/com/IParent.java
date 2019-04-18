package com;

/**
 * @author ZhangShaowei on 2019/4/16 13:35
 **/
public interface IParent<T> {

    default void sayHello() {
        System.out.println("Hello World!");
    }

}

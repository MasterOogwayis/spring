package com.zsw.demo.other;

/**
 * @author Shaowei Zhang on 2022/2/27 19:19
 **/
public interface B {

    default void s() {
        System.out.println("C");
    }

}

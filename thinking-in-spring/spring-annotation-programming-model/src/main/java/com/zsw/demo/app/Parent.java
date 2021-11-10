package com.zsw.demo.app;

/**
 * @author ZhangShaowei on 2021/11/10 10:05
 */
public interface Parent {

    default String hello() {
        return "This is Parent.";
    }

}

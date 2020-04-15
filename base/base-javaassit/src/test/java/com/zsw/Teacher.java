package com.zsw;

/**
 * @author ZhangShaowei on 2020/4/2 16:15
 */
public class Teacher {

    public Teacher() {
        throw new RuntimeException("不准实例化");
    }


    public void doSomething() {
        System.out.println("教书");
    }
}

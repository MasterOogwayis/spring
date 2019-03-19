package com.zsw.design.proxy;

/**
 * @author Shaowei Zhang on 2019/3/11 19:05
 **/
public class WorkTarget implements ObjectTarget {
    @Override
    public String doSomething() {

        System.out.println("doSomething......");

        return "do some thing...";


    }
}

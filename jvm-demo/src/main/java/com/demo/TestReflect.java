package com.demo;

import java.lang.reflect.Method;

/**
 * @author ZhangShaowei on 2019/3/15 10:03
 **/
public class TestReflect {


    public static void main(String[] args) throws Exception {

        Method test = Class.forName("com.demo.TestMethod").getMethod("test", new Class[0]);
        Object invoke = test.invoke(null);
        System.out.println(invoke);


    }




}

package com.zsw.design.proxy;

import com.zsw.design.proxy.istatic.StaticProxy;

/**
 * @author Shaowei Zhang on 2019/3/11 19:06
 **/
public class TestStaticProxy {

    public static void main(String[] args) {

        StaticProxy staticProxy = new StaticProxy(new WorkTarget());
        staticProxy.doSomething();

    }

}

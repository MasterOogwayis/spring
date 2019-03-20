package com.zsw.pattern.proxy;

import com.zsw.pattern.proxy.istatic.StaticProxy;
import com.zsw.pattern.proxy.target.Worker;

/**
 * @author ZhangShaowei on 2019/3/20 9:32
 **/
public class TestStaticProxy {

    public static void main(String[] args) {

        StaticProxy staticProxy = new StaticProxy(new Worker());
        String s = staticProxy.doSometing();
        System.err.println(s);

    }

}

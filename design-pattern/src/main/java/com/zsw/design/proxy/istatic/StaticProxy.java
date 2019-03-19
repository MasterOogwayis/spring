package com.zsw.design.proxy.istatic;

import com.zsw.design.proxy.ObjectTarget;
import lombok.AllArgsConstructor;

/**
 * @author Shaowei Zhang on 2019/3/11 19:04
 **/
@AllArgsConstructor
public class StaticProxy implements ObjectTarget {

    private ObjectTarget target;

    @Override
    public String doSomething() {
        System.out.println("静态代理...start");
        String str = this.target.doSomething();
        System.out.println("静态代理...end");
        return str;
    }
}

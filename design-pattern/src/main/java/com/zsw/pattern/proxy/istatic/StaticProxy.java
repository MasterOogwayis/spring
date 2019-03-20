package com.zsw.pattern.proxy.istatic;

import com.zsw.pattern.proxy.target.DoSomething;
import lombok.AllArgsConstructor;

/**
 * 静态代理
 *
 * 每次扩展方法都需要 硬编码扩展代理
 *
 * @author ZhangShaowei on 2019/3/20 9:29
 **/
@AllArgsConstructor
public class StaticProxy {

    private DoSomething doSomething;

    public String doSometing(){
        System.out.println("static proxy begin ...");
        String doSomething = this.doSomething.doSomething();
        System.out.println("static proxy end ...");
        return doSomething;
    }

}

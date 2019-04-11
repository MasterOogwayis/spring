package com;

/**
 * @author ZhangShaowei on 2019/4/4 16:51
 **/
public class TestFeignHttpClientHystrix implements TestFeignHttpClient {
    @Override
    public String sayHello(String name) {
        System.out.println(name);
        return "fallback + " + name;
    }
}

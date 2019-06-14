package com;

/**
 * @author ZhangShaowei on 2019/6/13 16:56
 **/
public class SomeMethod {

    public String sayHello(String name) {
        String str = "Hello " + name;
        System.out.println(str);
        return str;
    }

    public String sayHello(String name, Integer num) {
        String str = num + " Hello " + name;
        System.out.println(str);
        return str;
    }

}

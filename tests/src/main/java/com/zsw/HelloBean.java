package com.zsw;

import lombok.Setter;

/**
 * @author ZhangShaowei on 2020/12/29 16:37
 */
@Setter
public class HelloBean {

    private String message;

    public String hello(String name) {
        String str = this.message + ", Hello " + name;
        System.out.println(str);
        return str;
    }

}

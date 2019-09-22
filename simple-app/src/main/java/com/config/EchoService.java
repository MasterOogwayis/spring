package com.config;

import com.annotation.CustomComponemt;

/**
 * @author Administrator on 2019/9/16 22:06
 **/
@CustomComponemt
public class EchoService {

    public void echo(String name) {
        System.err.println(name);
    }

}

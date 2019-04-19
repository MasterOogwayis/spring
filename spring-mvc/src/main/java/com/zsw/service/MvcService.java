package com.zsw.service;

import org.springframework.stereotype.Service;

/**
 * @author Shaowei Zhang on 2019/4/18 0:13
 **/
@Service
public class MvcService {


    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }

}

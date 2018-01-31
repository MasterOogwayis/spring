package com.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a test demo.
 *
 * @author ZhangShaowei on 2018/1/26 14:32
 **/
@RestController
public class ResourceController {


    /**
     * @param name
     * @return
     */
    @PostMapping("hello")
    public String hello(final String name) {
        return "hello " + name;
    }


}

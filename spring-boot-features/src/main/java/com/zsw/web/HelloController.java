package com.zsw.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/8/18 11:29
 */
@RequestMapping("hello")
@RestController
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hello World";
    }

}

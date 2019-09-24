package com.zsw.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/9/18 11:08
 **/
@RestController
@RequestMapping("api")
public class TestController {

    @GetMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }

}

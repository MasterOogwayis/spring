package com.zsw.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/5/11 14:18
 */
@RestController
@RequestMapping("test")
public class HelloWorldApi {

    @GetMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("sum")
    public Object sum(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        return a + b;
    }


}

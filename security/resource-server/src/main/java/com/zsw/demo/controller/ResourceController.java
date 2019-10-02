package com.zsw.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/10/2 16:03
 **/
@RequestMapping("resource")
@RestController
public class ResourceController {


    @GetMapping("echo")
    public String echo(@RequestParam("name") String name) {
        return "Echo: " + name;
    }

    @GetMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }



}

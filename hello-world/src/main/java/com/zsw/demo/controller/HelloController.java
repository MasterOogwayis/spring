package com.zsw.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator on 2019/11/2 20:30
 **/
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String Hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }


    @RequestMapping("helloWorld")
    public String helloWorld() {
        return "Hello World!";
    }

}

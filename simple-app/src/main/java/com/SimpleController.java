package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@RequestMapping("test")
@RestController
public class SimpleController {

    @Resource(name = "simpleService")
    SimpleService simpleService;


    @GetMapping("Hello")
    public Object Hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }


}

package com.zsw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/10/2 19:26
 **/
@RequestMapping("echo")
@RestController
public class EchoController {

    @GetMapping
    public Object echo(@RequestParam("name") String name) {
        return "Echo: " + name;
    }

}

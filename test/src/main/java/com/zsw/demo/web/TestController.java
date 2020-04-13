package com.zsw.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/10/28 10:34
 **/
@RequestMapping("test")
@RestController
public class TestController {

    @GetMapping
    public Object ping() {
        return "pong";
    }


}

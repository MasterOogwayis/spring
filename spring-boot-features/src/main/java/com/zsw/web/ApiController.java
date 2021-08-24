package com.zsw.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/8/18 11:29
 */
@RestController
public class ApiController {


    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }


    @RequestMapping("body")
    private Object body(@RequestBody String json) {
        return json;
    }

}

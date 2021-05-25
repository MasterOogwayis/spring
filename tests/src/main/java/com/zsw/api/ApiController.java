package com.zsw.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/1/13 13:01
 */
@RestController
@RequestMapping("hello")
public class ApiController {

    @GetMapping
    public String get(@RequestParam("name") String name) {
        return "Hello " + name + "!";
    }

}

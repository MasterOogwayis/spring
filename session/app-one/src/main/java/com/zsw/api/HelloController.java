package com.zsw.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/7/8 14:39
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }

}

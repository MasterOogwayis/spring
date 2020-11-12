package com.zsw.test.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/5/11 14:18
 */
@RestController
@RequestMapping("hello")
public class HelloWorldApi {

    @Value("${random.int[9000,10000]}")
    private String random;

    @GetMapping
    public String world(@RequestParam("name") String name) {
        return "Hello " + name + random;
    }

}

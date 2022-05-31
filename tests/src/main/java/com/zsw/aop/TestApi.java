package com.zsw.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shaowei Zhang on 2022/3/20 20:02
 */
@RequestMapping("sayHello")
@RestController
public class TestApi {

    @Logging
    @GetMapping
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }


}

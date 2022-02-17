package com.zsw.api;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2022/2/17 10:30
 */
@AllArgsConstructor
@RestController
public class HelloWorldApi {


    @GetMapping("hello")
    public String hello() {
        return "Hello World!";
    }


    @Async
    public void t() {
        System.out.println("123");
    }

}

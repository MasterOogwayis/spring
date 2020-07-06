package com.zsw.test;

import com.zsw.test.client.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ZhangShaowei on 2020/5/11 14:29
 */
@RequestMapping("test")
@RestController
public class HelloController {

    @Autowired
    private HelloClient helloClient;



    @GetMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return this.helloClient.hello(name);
    }

}

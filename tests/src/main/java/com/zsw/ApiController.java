package com.zsw;

import com.zsw.service.ISayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/1/13 13:01
 */
@RequestMapping("hello")
@RestController
public class ApiController {

    @Autowired
    private ISayHelloService sayHelloService;

    @GetMapping
    public String hello(@RequestParam String name) {
        return this.sayHelloService.hello(name);
    }

}

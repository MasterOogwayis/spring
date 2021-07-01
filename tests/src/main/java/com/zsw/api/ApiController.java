package com.zsw.api;

import com.zsw.service.ISayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/1/13 13:01
 */
@RestController
public class ApiController {

    @Autowired
    private ISayHelloService helloService;

    @GetMapping("hello")
    public String get(@RequestParam("name") String name) {
        return this.helloService.hello(name);
    }

    @GetMapping("order")
    public Object order(@RequestParam("user") String user) {
        return this.helloService.order(user);
    }

}

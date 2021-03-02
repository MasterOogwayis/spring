package com.zsw.api;

import com.zsw.pojo.Customer;
import com.zsw.service.ISayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/1/13 13:01
 */
@RestController
@RequestMapping("hello")
public class ApiController {

    @Autowired
    private ISayHelloService sayHelloService;

    @GetMapping
    public String hello(@RequestParam String name) {
        return this.sayHelloService.hello(name);
    }

    @GetMapping("t")
    public String t(Customer customer) {
        return this.sayHelloService.hello("t: " + customer.getName());
    }

    @GetMapping("t2")
    public String t2(Customer customer, @RequestBody String body) {
        return this.sayHelloService.hello("t2: " + customer.getName());
    }

    @GetMapping("t3")
    public String t3(@RequestBody String body, Customer customer) {
        return this.sayHelloService.hello("t3: " + customer.getName());
    }

}

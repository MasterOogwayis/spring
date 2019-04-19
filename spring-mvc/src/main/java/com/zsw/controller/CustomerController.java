package com.zsw.controller;

import com.zsw.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shaowei Zhang on 2019/4/19 22:13
 **/
@RequestMapping("customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("get")
    public Object get(@RequestParam("id") Long id) {
        return this.customerService.get(id);
    }

    @GetMapping("findAll")
    public Object findAll() {
        return this.customerService.findAll();
    }

}

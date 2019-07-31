package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@Slf4j
@RestController
@RequestMapping("test")
public class SimpleController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("get")
    public Object test(@RequestParam("id") Long id) {
        return this.customerRepository.getOne(id);
    }


    @GetMapping("find")
    public Object find() {
        List<Customer> customers = this.customerRepository.find("from  Customer");
        return customers;
    }


}

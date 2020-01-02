package com.zsw.controller;

import com.zsw.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/11/14 13:48
 **/
@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("t1")
    @Transactional(rollbackFor = Exception.class)
    public Object t1() {
        return customerRepository.t1();
    }


}

package com.zsw.controller;

import com.zsw.persistence.dao.CustomerMapper;
import com.zsw.persistence.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Shaowei Zhang on 2019/4/24 1:14
 **/
@RequestMapping("customer")
@RestController
public class MyBatisTempController {

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping("add")
    public Object get(@RequestBody Customer customer) {
        customer.setCreatedate(new Date());
        return this.customerMapper.insert(customer);
    }


}

package com.zsw.controller;

import com.zsw.persistence.dao.CustomerMapper;
import com.zsw.persistence.entity.Customer;
import com.zsw.persistence.entity.CustomerExample;
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
    public Object add(@RequestBody Customer customer) {
        customer.setCreatedate(new Date());
        return this.customerMapper.insert(customer);
    }

    @GetMapping("get")
    public Customer get(@RequestParam("id") Long id) {
        CustomerExample example = new CustomerExample();
        example.createCriteria().andIdEqualTo(id);
        return this.customerMapper.selectByExample(example).get(0);
    }

}

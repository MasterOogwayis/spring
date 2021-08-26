package com.zsw.api;

import com.zsw.persistence.entity.Customer;
import com.zsw.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/26 10:45
 */
@RequestMapping("customer")
@RestController
public class CustomerApi {

    @Autowired
    private CustomerService customerService;

    @GetMapping("find")
    public List<Customer> findAll() {
        return this.customerService.findAll();
    }

}

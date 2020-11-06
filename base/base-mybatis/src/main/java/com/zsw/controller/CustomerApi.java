package com.zsw.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsw.persistence.entity.Customer;
import com.zsw.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/11/6 10:09
 */
@AllArgsConstructor
@RestController
@RequestMapping("customer")
public class CustomerApi {

    private final CustomerService customerService;


    @PostMapping("add")
    public Customer add(@RequestBody Customer customer) {
        this.customerService.save(customer);
        return customer;
    }

    @GetMapping("find")
    public Page<Customer> find(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return this.customerService.page(new Page<>(page, pageSize));
    }

    @GetMapping("get")
    public Customer get(@RequestParam("id") Long id) {
        return this.customerService.getById(id);
    }

}

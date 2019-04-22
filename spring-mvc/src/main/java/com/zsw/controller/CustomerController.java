package com.zsw.controller;

import com.zsw.persistence.entity.Customer;
import com.zsw.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("save")
    public Object save(@RequestBody Customer customer) {
        return this.customerService.save(customer);
    }

    @PostMapping("update")
    public Object update(@RequestBody Customer customer) {
        this.customerService.update(customer);
        return "success";
    }

    @GetMapping("delete")
    public Object delete(@RequestParam("id") Long id) {
        this.customerService.delete(id);
        return "success";
    }
    @PostMapping("saveOrUpdate")
    public Object saveOrUpdate(@RequestBody Customer customer) {
        this.customerService.saveOrUpdate(customer);
        return "success";
    }


}

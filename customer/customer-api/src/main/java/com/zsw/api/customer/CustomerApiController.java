package com.zsw.api.customer;

import com.zsw.persistence.bean.Customer;
import com.zsw.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangShaowei on 2017/9/15 11:01
 */
@RestController
@RequestMapping("customer")
public class CustomerApiController {

    /**
     *
     */
    @Autowired
    private CustomerService customerService;


    /**
     * @param id id
     * @return Customer
     */
    @PostMapping("post")
    public Customer post(@RequestParam("id") final Long id) {
        Customer customer = this.customerService.get(id);
        return customer;
    }

    /**
     * @param id id
     * @return Customer
     */
    @GetMapping("get")
    public Customer get(@RequestParam("id") final Long id) {
        Customer customer = this.customerService.get(id);
        return customer;
    }

    /**
     * @param customer Customer
     * @return Customer
     */
    @PostMapping("add")
    public Customer add(@RequestBody final Customer customer) {
        return this.customerService.saveOrUpdate(customer);
    }

    /**
     * @param id
     * @return
     */
    @PostMapping("lock")
    public Customer lock(@RequestParam final Long id) {
        return this.customerService.lock(id);
    }


}

package com.zsw.aop;

import com.zsw.peprsistence.entity.Customer;
import com.zsw.peprsistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shaowei Zhang on 2022/3/16 20:09
 */
@RequestMapping("hello")
@RestController
public class HelloApi implements HelloInterface {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    @EchoHello
    @GetMapping
    public String hello() {
        Customer customer = Customer.builder()
                .address("address")
                .age(18)
                .idNo("idNo")
                .name("name")
                .build();
        this.customerRepository.save(customer);
        return "Hello World";
    }

}


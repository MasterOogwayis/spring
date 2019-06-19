package com;

import com.demo.persistence.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@Slf4j
@RequestMapping("get")
@RestController
public class SimpleController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public Object get(@RequestParam("id") Long id) {
        return customerRepository.getOne(id);
    }


}

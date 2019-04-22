package com.zsw.service;

import com.zsw.persistence.entity.Customer;
import com.zsw.persistence.repository.CustomerRerpository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shaowei Zhang on 2019/4/19 22:16
 **/
@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerRerpository customerRerpository;

//    @Autowired
//    private NamedParameterJdbcOperations namedParameterJdbcOperations;


    @SneakyThrows
    public Customer get(Long id) {
        return this.customerRerpository.get(id);
    }

    public List<Customer> findAll() {
        return this.customerRerpository.findAll();
    }


}

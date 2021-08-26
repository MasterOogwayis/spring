package com.zsw.service;

import com.zsw.persistence.entity.Customer;
import com.zsw.persistence.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/26 10:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerService extends BaseService<CustomerRepository, Customer, Long> {

    public List<Customer> find() {
        return super.repository.findAll();
    }

}

package com.zsw.service.impl;

import com.zsw.persistence.entity.Customer;
import com.zsw.service.CustomerService;
import org.springframework.stereotype.Service;

/**
 * @author Shaowei Zhang on 2019/4/24 1:20
 **/
@Service
public class CustomerServiceImpl implements CustomerService {



    @Override
    public Customer get(Long id) {
        return null;
    }

    @Override
    public int save(Customer customer) {
        return 0;
    }
}

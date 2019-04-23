package com.zsw.service;

import com.zsw.persistence.entity.Customer;

/**
 * @author Shaowei Zhang on 2019/4/24 1:19
 **/
public interface CustomerService {

    Customer get(Long id);

    int save(Customer customer);

}

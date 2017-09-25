package com.zsw.persistence.repository;

import com.zsw.base.repository.BaseRepository;
import com.zsw.persistence.bean.Customer;

/**
 * CustomerRepository
 *
 * @author ZhangShaowei on 2017/9/15 10:29
 **/
//@Repository
//@Table(name = "CUSTOMER")
//@Qualifier("customerRepository")
public interface CustomerRepository extends BaseRepository<Customer, Long> {
}

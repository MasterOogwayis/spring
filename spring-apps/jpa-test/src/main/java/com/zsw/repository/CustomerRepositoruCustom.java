package com.zsw.repository;

import com.zsw.orm.repository.BaseCustomRepository;
import com.zsw.repository.entity.Customer;

import java.util.List;

/**
 * @author ZhangShaowei on 2019/12/26 11:15
 **/
public interface CustomerRepositoruCustom extends BaseCustomRepository<Customer, Long> {

    List<CustomerDto> t1();
}

package com.zsw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsw.persistence.entity.Customer;
import com.zsw.persistence.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2020/11/6 10:08
 */
@Service
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> implements IService<Customer> {



}

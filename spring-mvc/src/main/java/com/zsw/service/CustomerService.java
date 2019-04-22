package com.zsw.service;

import com.zsw.persistence.entity.Customer;
import com.zsw.persistence.repository.CustomerRerpository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public int save(Customer customer) {
        customer.setCreateDate(new Date());
        return this.customerRerpository.save(customer);
    }

    public void update(Customer customer) {
        Customer old = this.customerRerpository.get(customer.getId());
        BeanUtils.copyProperties(customer, old, "createDate");
        this.customerRerpository.update(old);
    }

    public void delete(Long id) {
        this.customerRerpository.delete(id);
    }

    public void saveOrUpdate(Customer customer) {
        Customer c = this.customerRerpository.get(customer.getId());
        BeanUtils.copyProperties(customer, c);
        this.customerRerpository.saveOrUpdate(customer);
    }


}

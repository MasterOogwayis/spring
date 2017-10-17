package com.zsw.service.customer;

import com.zsw.base.cache.annotation.CacheLock;
import com.zsw.base.service.BaseService;
import com.zsw.persistence.bean.Customer;
import com.zsw.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangShaowei on 2017/9/15 10:55
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerService extends BaseService {

    /**
     *
     */
    @Autowired
    private CustomerRepository customerRepository;


    /**
     * @param id id
     * @return Customer
     */
    @Cacheable(value = "customers", key = "'customer:' + #id", condition = "#id != null")
    public Customer get(final Long id) {
        return this.customerRepository.getOne(id);
    }

    /**
     * @param customer Customer
     */
    public Customer saveOrUpdate(final Customer customer) {
        return this.customerRepository.saveOrUpdate(customer);
    }

    /**
     * @param id id
     * @return
     */
    @CacheLock(key = "'customer' + #id")
    public Customer lock(final Long id) {
        return this.customerRepository.getOne(id);
    }


}

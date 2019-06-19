package com.demo.persistence.repository;

import com.demo.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ZhangShaowei on 2019/6/19 16:41
 **/
public interface CustomerRepository extends JpaRepository<Customer, Long> {


}

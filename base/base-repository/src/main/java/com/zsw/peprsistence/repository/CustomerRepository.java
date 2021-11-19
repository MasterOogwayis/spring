package com.zsw.peprsistence.repository;

import com.zsw.peprsistence.entity.Customer;
import com.zsw.repository.QueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangShaowei on 2021/11/18 10:07
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, QueryDslRepository<Customer, Long> {
}

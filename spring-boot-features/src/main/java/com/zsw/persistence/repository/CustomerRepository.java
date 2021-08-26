package com.zsw.persistence.repository;

import com.zsw.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangShaowei on 2021/8/26 10:33
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

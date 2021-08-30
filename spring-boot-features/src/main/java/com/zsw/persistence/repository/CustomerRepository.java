package com.zsw.persistence.repository;

import com.zsw.persistence.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/26 10:33
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("from Customer")
    List<Customer> findAllByPage(Pageable pageable);

    List<Customer> findByPhone(String phone);

    @Query("from Customer where name = :name")
    List<Customer> findByName(@Param("name") String name);

}

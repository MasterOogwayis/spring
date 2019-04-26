package com.zsw.persistence.dao;

import com.zsw.persistence.entity.Customer;
import com.zsw.persistence.entity.CustomerExample;

import java.util.HashMap;
import java.util.List;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbg.generated
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbg.generated
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbg.generated
     */
    List<Customer> selectByExample(CustomerExample example);

    List<Customer> findAll();


    Customer get(Long id);

    List<Customer> findByIds(List<Long> list);

    List<Customer> findByColumns(Customer customer);

    int update(Customer customer);

}
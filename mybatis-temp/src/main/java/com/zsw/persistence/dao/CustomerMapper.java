package com.zsw.persistence.dao;

import com.zsw.persistence.entity.Customer;
import com.zsw.persistence.entity.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbg.generated
     */
    long countByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbg.generated
     */
    int deleteByExample(CustomerExample example);

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

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);
}
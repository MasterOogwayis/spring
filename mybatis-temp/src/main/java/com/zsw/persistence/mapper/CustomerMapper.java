package com.zsw.persistence.mapper;

import com.zsw.persistence.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shaowei Zhang on 2019/4/24 1:22
 **/
@Mapper
public interface CustomerMapper {

    Customer get(Long id);

    int save(Customer customer);

}

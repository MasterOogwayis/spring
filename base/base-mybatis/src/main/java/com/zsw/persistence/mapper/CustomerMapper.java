package com.zsw.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsw.persistence.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhangShaowei on 2020/11/6 10:07
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}

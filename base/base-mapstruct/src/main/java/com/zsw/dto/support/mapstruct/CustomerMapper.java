package com.zsw.dto.support.mapstruct;

import com.zsw.demo.Customer;
import com.zsw.demo.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ZhangShaowei on 2019/10/11 10:44
 * 也可作为 spring bean 注入 @Mapper(componentModel = "spring")
 **/
@Mapper
public interface CustomerMapper extends StandardMapper<Customer, CustomerDto> {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

}

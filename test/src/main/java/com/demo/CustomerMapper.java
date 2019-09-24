package com.demo;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author ZhangShaowei on 2019/7/16 16:55
 **/
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mappings({})
    CustomerDto toDto(Customer customer);
}

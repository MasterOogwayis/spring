//package com.mapper;
//
//import com.zsw.pojo.mapper.base.StandardMapper;
//import org.mapstruct.BeanMapping;
//import org.mapstruct.Mapper;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.NullValuePropertyMappingStrategy;
//import org.mapstruct.factory.Mappers;
//
///**
// * @author ZhangShaowei on 2020/1/3 16:10
// */
//@Mapper
//public interface CustomerMapper extends StandardMapper<Customer, CustomerDto> {
//
//    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
//
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
//    void copyProperties(@MappingTarget CustomerDto target, Customer source);
//
//
//}

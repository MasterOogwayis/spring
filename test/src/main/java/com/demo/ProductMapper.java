package com.demo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author ZhangShaowei on 2019/7/16 16:55
 **/
@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "key"),
            @Mapping(source = "name", target = "productName")
    })
    ProductDTO toDto(Product product);
}

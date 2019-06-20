package com.demo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author ZhangShaowei on 2019/6/20 15:28
 **/
@Mapper
public interface ProductConverter {

    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "key"),
            @Mapping(source = "name", target = "productName"),
    })
    ProductDTO domain2dto(Product product);

    List<ProductDTO> domain2dto(List<Product> products);

}

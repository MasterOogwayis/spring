package com;

import com.zsw.dto.support.mapstruct.StandardMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author ZhangShaowei on 2019/12/27 11:23
 **/
@Mapper(uses = MapperUtils.class)
public interface CustomerDtoMapper extends StandardMapper<CustomerDto, Dto> {

    CustomerDtoMapper INSTANCE = Mappers.getMapper(CustomerDtoMapper.class);


    @Override
    @Mappings({
            @Mapping(target = "amount", defaultValue = "0.00", qualifiedBy = FenToYuanHalfUp.class),
            @Mapping(target = "price", defaultValue = "0.00", qualifiedBy = FenToYuan.class),
            @Mapping(target = "date1", qualifiedBy = DateTimeFormat.class),
            @Mapping(target = "date2", qualifiedBy = DateFormat.class),
    })
    CustomerDto from(Dto source);

    @Mappings({
            @Mapping(target = "amount", source = "amount", defaultValue = "0.00", qualifiedBy = FenToYuanHalfUp.class),
            @Mapping(target = "price", defaultValue = "0.00", qualifiedBy = FenToYuan.class),
            @Mapping(target = "date1", qualifiedBy = DateTimeFormat.class),
            @Mapping(target = "date2", qualifiedBy = DateFormat.class),
    })
    CustomerDto from(Dto source, Double amount);

}

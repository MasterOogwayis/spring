package com.zsw.dto.support.mapstruct;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * @param <E> Entity
 * @param <D> pojo
 * @author zsw on 2019/8/10 20:33
 */
//@Mapper(componentModel = "spring")
public interface StandardMapper<E, D> {


    @Mappings({})
    D map(E source);

    @Mappings({})
    List<D> map(List<E> sources);

    @Mappings({})
    E from(D source);

    @Mappings({})
    List<E> from(List<D> sources);

    /**
     * 基本也就只有更新 entity 的情况
     *
     * @param source pojo
     * @param target target会被更新，忽略 null
     * @return entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E update(@MappingTarget E target, D source);

}

package com.zsw.dto.support.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @param <E> Entity
 * @param <D> Dto
 * @author Administrator on 2019/8/10 20:33
 */
//@Mapper(componentModel = "spring")
public interface BaseMapper<E, D> {

    @Mappings({})
    E dtoToEntity(D d);

    @Mappings({})
    List<E> dtosToList(List<D> list);

    @Mappings({})
    D entityToDto(E e);

    @Mappings({})
    List<D> entitiesToList(List<E> list);

}

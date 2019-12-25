package com.zsw.dto.support.mapstruct;

import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @param <E> Entity
 * @param <D> pojo
 * @author zsw on 2019/8/10 20:33
 */
//@Mapper(componentModel = "spring")
public interface StandardMapper<E, D> {


    @Mappings({})
    E map(D d);

    @Mappings({})
    List<E> map(List<D> list);

    @Mappings({})
    D to(E e);

    @Mappings({})
    List<D> to(List<E> list);

    /**
     * 基本也就只有更新 entity 的情况
     *
     * @param d pojo
     * @param e entity
     * @return entity
     */
    E update(D d, @MappingTarget E e);

}

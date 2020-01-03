package com.zsw.pojo.mapper.base;

import com.zsw.pojo.mapper.annotation.Simple;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * 注意 MapStruct 属于浅拷贝
 * 不同类型的 集合类属性都会自动处理
 * <p>
 * MapStruct 方法识别顺序
 * *** MapStruct has a predefined order of attempts:
 * <p>
 * 1. User provided Mapping method
 * 2. Direct (types source -target are the same)
 * 3. Mapping method (built-in)
 * 4. Type conversion
 * <p>
 * <p>
 * *** If this all fails MapStruct tries to do a number of 2 step approaches:
 * <p>
 * 5. mapping method - mapping method
 * 6. mapping method - type conversion
 * 7. type conversion - mapping method
 *
 * @param <E> Entity
 * @param <D> pojo
 * @author zsw on 2019/8/10 20:33
 */
//@Mapper(componentModel = "spring")
public interface StandardMapper<E, D> {


    @Mappings({})
    D map(E entity);

    @Mappings({})
    @IterableMapping(qualifiedBy = Simple.class)
    List<D> map(List<E> list);

    @Mappings({})
    E from(D dto);

    @Mappings({})
    @IterableMapping(qualifiedBy = Simple.class)
    List<E> from(List<D> list);

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

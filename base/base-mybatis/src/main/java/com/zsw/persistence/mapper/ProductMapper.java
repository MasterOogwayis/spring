package com.zsw.persistence.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zsw.persistence.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shaowei Zhang
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    default List<String> findNames() {
        LambdaQueryWrapper<Product> select = Wrappers.<Product>lambdaQuery().select(Product::getProductName);
        List<Object> objects = this.selectObjs(select);
        return objects.stream().map(Object::toString).collect(Collectors.toList());
    }


}
package com.zsw.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsw.entity.ProductInfoIntercity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ZhangShaowei on 2020/7/8 9:30
 */
@Mapper
public interface ProductInfoIntercityMapper extends BaseMapper<ProductInfoIntercity> {

    default List<ProductInfoIntercity> findAllProductCode(){
        LambdaQueryWrapper<ProductInfoIntercity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ProductInfoIntercity::getProductCode);
        return this.selectList(queryWrapper);
    };


}

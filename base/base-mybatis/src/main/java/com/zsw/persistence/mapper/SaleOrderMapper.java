package com.zsw.persistence.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsw.persistence.ExtendBaseMapper;
import com.zsw.persistence.entity.SaleOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ZhangShaowei on 2020/5/12 9:53
 */
@Mapper
public interface SaleOrderMapper extends ExtendBaseMapper<SaleOrder> {

    List<SaleOrder> findAll();


    default List<SaleOrder> find() {
        LambdaQueryWrapper<SaleOrder> queryWrapper = lambdaQueryWarpper().select(SaleOrder::getOrderNumber, SaleOrder::getCreateDate);
        return this.selectList(queryWrapper);
    }

}

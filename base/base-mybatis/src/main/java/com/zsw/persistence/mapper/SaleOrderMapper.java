package com.zsw.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsw.persistence.entity.SaleOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ZhangShaowei on 2020/5/12 9:53
 */
@Mapper
public interface SaleOrderMapper extends BaseMapper<SaleOrder> {

    List<SaleOrder> findAll();

}

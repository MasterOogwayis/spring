package com.zsw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsw.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ZhangShaowei on 2020/7/8 15:38
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    int batchInsert(List<Product> list);

}

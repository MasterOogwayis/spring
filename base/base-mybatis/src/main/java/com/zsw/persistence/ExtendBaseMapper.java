package com.zsw.persistence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2020/6/30 16:53
 */
public interface ExtendBaseMapper<T extends Serializable> extends BaseMapper<T> {

    default QueryWrapper<T> queryWarpper() {
        return new QueryWrapper<>();
    }

    default LambdaQueryWrapper<T> lambdaQueryWarpper() {
        return new LambdaQueryWrapper<>();
    }

}

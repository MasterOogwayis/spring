package com.zsw.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsw.persistence.entity.Country;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shaowei Zhang
 */
@Mapper
public interface CountryMapper extends BaseMapper<Country> {
}
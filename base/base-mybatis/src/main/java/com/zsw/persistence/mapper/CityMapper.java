package com.zsw.persistence.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsw.persistence.entity.City;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shaowei Zhang
 */
@Mapper
public interface CityMapper extends BaseMapper<City> {

    default City getByName(String name) {
        LambdaQueryWrapper<City> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(City::getName, name)
                .select(City::getId, City::getName, City::getCountryCode, City::getDistrict);
        return this.selectList(queryWrapper).get(0);
    }


}
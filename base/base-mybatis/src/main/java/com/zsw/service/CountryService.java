package com.zsw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsw.persistence.entity.Country;
import com.zsw.persistence.mapper.CountryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Shaowei Zhang on 2020/7/27 21:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CountryService extends ServiceImpl<CountryMapper, Country> implements IService<Country> {
}

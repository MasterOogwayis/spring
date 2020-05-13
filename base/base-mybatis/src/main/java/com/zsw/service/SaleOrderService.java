package com.zsw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsw.persistence.entity.SaleOrder;
import com.zsw.persistence.mapper.SaleOrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangShaowei on 2020/5/12 11:24
 */
@Service
public class SaleOrderService extends ServiceImpl<SaleOrderMapper, SaleOrder> implements IService<SaleOrder> {

    @Transactional
    public List<SaleOrder> findAll() {
        return this.baseMapper.findAll();
    }


}

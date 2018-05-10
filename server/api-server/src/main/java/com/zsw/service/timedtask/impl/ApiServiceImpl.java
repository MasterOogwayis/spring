package com.zsw.service.timedtask.impl;

import com.zsw.base.service.impl.BaseServiceImpl;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.user.bean.SaleOrder;
import com.zsw.service.ApiService;
import com.zsw.service.saleorder.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangShaowei on 2018/1/12 14:49
 */
@Service
public class ApiServiceImpl extends BaseServiceImpl implements ApiService {

    /**
     *
     */
    @Autowired
    private SaleOrderService saleOrderService;

    /**
     * @param saleOrderDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaleOrder edit(final SaleOrderDto saleOrderDto) {
        return this.saleOrderService.edit(saleOrderDto);
    }


    @Override
    public JpaRepository getRepository() {
        return null;
    }
}

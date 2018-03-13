package com.zsw.service;

import com.zsw.base.service.BaseService;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.bean.SaleOrder;
import com.zsw.service.saleorder.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangShaowei on 2018/1/12 14:49
 */
@Service
public class ApiService extends BaseService {

    /**
     *
     */
    @Autowired
    private SaleOrderService saleOrderService;

    /**
     * @param saleOrderDto
     * @return
     */
//    @CacheLock(key = "#saleOrderDto.getId()")
    @Transactional(rollbackFor = Exception.class)
    public SaleOrder edit(final SaleOrderDto saleOrderDto) {
        return this.saleOrderService.edit(saleOrderDto);
    }


}

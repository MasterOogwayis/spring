package com.zsw.service;

import com.zsw.base.service.BaseService;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.user.bean.SaleOrder;

/**
 * ApiService
 *
 * @author ZhangShaowei on 2018/1/12 14:49
 */
public interface ApiService extends BaseService {

    /**
     * @param saleOrderDto
     * @return
     */
    SaleOrder edit(final SaleOrderDto saleOrderDto);


}

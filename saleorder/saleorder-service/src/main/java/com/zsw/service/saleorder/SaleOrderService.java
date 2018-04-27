package com.zsw.service.saleorder;

import com.zsw.base.service.BaseService;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.timedtask.bean.SaleOrder;

/**
 * @author ZhangShaowei on 2017/10/12 15:15
 */
public interface SaleOrderService extends BaseService<SaleOrder, Long> {


    /**
     * @param saleOrderDto
     * @return
     */
    SaleOrder edit(SaleOrderDto saleOrderDto);

    /**
     * @param id  product id
     * @param num num
     * @return
     */
    String order(Long id, Long num);

    SaleOrder getCached(Long id);


}

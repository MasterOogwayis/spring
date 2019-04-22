package com.zsw.service.saleorder;

import com.zsw.orm.service.BaseDataService;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.user.bean.SaleOrder;

/**
 * @author ZhangShaowei on 2017/10/12 15:15
 */
public interface SaleOrderService extends BaseDataService<SaleOrder, Long> {


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

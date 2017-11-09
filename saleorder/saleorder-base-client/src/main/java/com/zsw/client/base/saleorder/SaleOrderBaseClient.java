package com.zsw.client.base.saleorder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhangShaowei on 2017/11/6 16:05
 */
public interface SaleOrderBaseClient {

    @RequestMapping(value = "saleorder/get", method = RequestMethod.GET)
    SaleOrderDto get(@RequestParam("id") final Long id);


}

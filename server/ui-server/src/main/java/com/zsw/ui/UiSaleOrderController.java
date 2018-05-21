package com.zsw.ui;

import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.conf.saleorder.SaleOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UiSaleOrderController
 *
 * @author ZhangShaowei on 2018/5/18 14:23
 **/
@RestController
@RequestMapping("saleorder")
public class UiSaleOrderController {

    /**
     *
     */
    @Autowired
    private SaleOrderClient saleOrderClient;

    @GetMapping("get")
    public SaleOrderDto get(Long id) {
        return this.saleOrderClient.get(id);
    }

}

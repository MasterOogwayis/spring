package com;

import com.zsw.client.base.saleorder.SaleOrderDto;
import com.zsw.client.saleorder.SaleOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/12/18 15:45
 */
@RestController
public class UiSaleOrderController {


    /**
     *
     */
    @Autowired
    private UiSaleOrderService saleOrderService;

    /**
     *
     */
    @Autowired
    private SaleOrderClient saleOrderClient;


    @GetMapping("get")
    public SaleOrderDto get(@RequestParam final Long id) {
        long timer = System.currentTimeMillis();
        SaleOrderDto dto = this.saleOrderService.get(id);
        System.err.println("get:" + (System.currentTimeMillis() - timer));
        return dto;
    }


    @GetMapping("post")
    public SaleOrderDto post(@RequestParam final Long id) {
        long timer = System.currentTimeMillis();
        SaleOrderDto dto = this.saleOrderClient.get(id);
        System.err.println("post:" + (System.currentTimeMillis() - timer));
        return dto;
    }



}

package com.zsw.controller.saleorder;

import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.conf.saleorder.SaleOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/11/6 16:34
 */
@RestController
@RequestMapping("saleorder")
public class SaleOrderController {

    /**
     *
     */
    @Autowired
    private SaleOrderClient saleOrderClient;


    /**
     * @param id
     * @return
     */
    @GetMapping("get")
    public SaleOrderDto get(@RequestParam final Long id) {
        return this.saleOrderClient.get(id);
    }

}

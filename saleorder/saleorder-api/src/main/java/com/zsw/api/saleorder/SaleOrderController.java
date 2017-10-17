package com.zsw.api.saleorder;

import com.zsw.base.api.commons.BaseApiController;
import com.zsw.persistence.bean.SaleOrder;
import com.zsw.service.saleorder.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangShaowei on 2017/10/12 15:21
 */
@RestController
@RequestMapping("saleorder")
public class SaleOrderController extends BaseApiController {

    /**
     *
     */
    @Autowired
    private SaleOrderService saleOrderService;


    /**
     * @param id product id
     * @return
     */
    @PostMapping("test")
    public String test(@RequestParam final Long id) {
        saleOrderService.concurrent(id);
        return "success";
    }

    /**
     * @return
     */
    @PostMapping("cache")
    public String cachelock() {
        return this.saleOrderService.test(1L);
    }

    /**
     * @param id id
     * @return SaleOrder
     */
    @GetMapping("get")
    public SaleOrder get(@RequestParam final Long id) {
        return this.saleOrderService.getSaleOrder(id);
    }

}

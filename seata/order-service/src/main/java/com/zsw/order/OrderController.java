package com.zsw.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/6/7 13:17
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/create", produces = "application/json")
    public Boolean create(String userId, String commodityCode, Integer count) {

        orderService.create(userId, commodityCode, count);
        return true;
    }

}

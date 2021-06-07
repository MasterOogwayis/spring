package com.zsw.order;

import com.zsw.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2021/6/7 13:18
 */
@Service
public class OrderService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(String userId, String commodityCode, Integer count) {

        int orderMoney = count * 100;
        jdbcTemplate.update("insert order_tbl(user_id,commodity_code,count,money) values(?,?,?,?)",
                new Object[]{userId, commodityCode, count, orderMoney});
        userFeignClient.reduce(userId, orderMoney);

    }

}

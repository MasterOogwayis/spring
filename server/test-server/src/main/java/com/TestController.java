package com;

import com.zsw.client.base.saleorder.SaleOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/11/9 10:58
 */
@RestController
public class TestController {

    /**
     *
     */
    @Autowired
    private TestClient testClient;


    /**
     * @param id
     * @return
     */
    @GetMapping("get")
    public SaleOrderDto get(@RequestParam final Long id) {
        return this.testClient.get(id);
    }

}

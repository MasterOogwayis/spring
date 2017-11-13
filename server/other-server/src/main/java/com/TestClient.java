package com;

import com.zsw.client.base.saleorder.SaleOrderDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhangShaowei on 2017/11/6 16:05
 */
@FeignClient(value = "${com.zsw.servername.saleorder}", fallback = TestClientImpl.class)
public interface TestClient {

    @RequestMapping(value = "saleorder/get", method = RequestMethod.GET)
    SaleOrderDto get(@RequestParam("id") final Long id);

}

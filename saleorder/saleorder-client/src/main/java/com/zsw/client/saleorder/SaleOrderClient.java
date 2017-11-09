package com.zsw.client.saleorder;

import com.zsw.client.base.saleorder.SaleOrderBaseClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author ZhangShaowei on 2017/11/6 16:05
 */
@FeignClient(value = "${com.zsw.servername.saleorder}", fallback = SaleOrderClientImpl.class)
public interface SaleOrderClient extends SaleOrderBaseClient {




}

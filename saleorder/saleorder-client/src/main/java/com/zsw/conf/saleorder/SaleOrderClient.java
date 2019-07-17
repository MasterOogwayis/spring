package com.zsw.conf.saleorder;

import com.zsw.conf.base.saleorder.SaleOrderBaseClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author ZhangShaowei on 2017/11/6 16:05
 */
@FeignClient(value = "${com.zsw.server.saleorder}", fallback = SaleOrderClientImpl.class)
public interface SaleOrderClient extends SaleOrderBaseClient {




}

package com.zsw.client.saleorder;

import com.zsw.client.base.saleorder.SaleOrderDto;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2017/11/6 16:09
 */
@Component
public class SaleOrderClientImpl implements SaleOrderClient {
    @Override
    public SaleOrderDto get(Long id) {
        SaleOrderDto saleOrderDto = new SaleOrderDto();
        saleOrderDto.setId(0L);
        return saleOrderDto;
    }
}

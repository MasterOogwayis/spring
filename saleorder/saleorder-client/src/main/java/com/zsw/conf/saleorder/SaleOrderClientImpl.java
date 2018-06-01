package com.zsw.conf.saleorder;

import com.zsw.conf.base.saleorder.SaleOrderDto;
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

    @Override
    public SaleOrderDto edit(SaleOrderDto dto) {
        SaleOrderDto saleOrderDto = new SaleOrderDto();
        saleOrderDto.setId(0L);
        return saleOrderDto;
    }
}

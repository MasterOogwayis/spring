package com.zsw.api.saleorder;

import com.zsw.base.api.commons.BaseApiController;
import com.zsw.conf.base.saleorder.ProductDto;
import com.zsw.conf.base.saleorder.SaleOrderBaseClient;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.user.bean.SaleOrder;
import com.zsw.service.saleorder.SaleOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ApiSaleOrderController
 *
 * @author ZhangShaowei on 2017/10/12 15:21
 */
@RestController
@RequestMapping("saleorder")
public class ApiSaleOrderController extends BaseApiController implements SaleOrderBaseClient {


    /**
     *
     */
    @Autowired
    private SaleOrderService saleOrderService;


    /**
     * @param id id
     * @return SaleOrder
     */
    @Override
    @GetMapping("get")
    public SaleOrderDto get(@RequestParam final Long id) {

        SaleOrder saleOrder = this.saleOrderService.get(id);
        SaleOrderDto saleOrderDto = new SaleOrderDto();
        BeanUtils.copyProperties(saleOrder, saleOrderDto, "PRODUCT");
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(saleOrder.getProduct(), productDto);
        saleOrderDto.setProduct(productDto);
        return saleOrderDto;
    }


    /**
     * 编辑SaleOrder
     *
     * @param saleOrderDto SaleOrderDto
     * @return SaleOrder
     */
    @PostMapping("edit")
    public SaleOrder edit(@ModelAttribute final SaleOrderDto saleOrderDto) {
        return this.saleOrderService.edit(saleOrderDto);
    }

}

package com.zsw.api.saleorder;

import com.zsw.base.api.commons.BaseApiController;
import com.zsw.base.utils.SpringContextUtils;
import com.zsw.conf.base.saleorder.ProductDto;
import com.zsw.conf.base.saleorder.SaleOrderBaseClient;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.bean.SaleOrder;
import com.zsw.service.saleorder.SaleOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
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

        SaleOrder saleOrder = this.saleOrderService.getSaleOrder(id);
        SaleOrderDto saleOrderDto = new SaleOrderDto();
        BeanUtils.copyProperties(saleOrder, saleOrderDto, "PRODUCT");
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(saleOrder.getProduct(), productDto);
        saleOrderDto.setProduct(productDto);
        return saleOrderDto;
    }


    /**
     * @param saleOrderDto
     * @return
     */
    @PostMapping("edit")
    public SaleOrder edit(@ModelAttribute final SaleOrderDto saleOrderDto) {
        SaleOrder saleOrder = this.saleOrderService.edit(saleOrderDto);
        System.err.println("Commited!");
        return saleOrder;
    }

}

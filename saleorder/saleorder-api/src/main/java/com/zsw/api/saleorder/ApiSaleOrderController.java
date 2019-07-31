package com.zsw.api.saleorder;

import com.zsw.orm.api.commons.BaseApiController;
import com.zsw.conf.base.saleorder.ProductDto;
import com.zsw.conf.base.saleorder.SaleOrderBaseClient;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.user.bean.SaleOrder;
import com.zsw.service.saleorder.SaleOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public SaleOrderDto edit(@RequestBody final SaleOrderDto saleOrderDto) {
        SaleOrder saleOrder = this.saleOrderService.edit(saleOrderDto);
        SaleOrderDto data = new SaleOrderDto();
        BeanUtils.copyProperties(saleOrder, data, "product");
        return data;
    }

    @GetMapping("find")
    public List<SaleOrder> find(@RequestParam("ids") String ids) {
        List<SaleOrder> collect = Stream.of(ids.split(",")).parallel().map(Long::valueOf).map(this.saleOrderService::get).collect(Collectors.toList());
        return collect;
    }

}

package com.zsw.api;

import com.zsw.base.redis.dao.commons.BaseCacheDao;
import com.zsw.cache.Address;
import com.zsw.cache.AddressRepository;
import com.zsw.cache.SaleOrderCache;
import com.zsw.conf.base.saleorder.ProductDto;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.bean.SaleOrder;
import com.zsw.service.ApiService;
import com.zsw.service.saleorder.SaleOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Executor;

/**
 * @author ZhangShaowei on 2018/1/12 14:48
 */
@RestController
@RequestMapping("api")
public class ApiController {

    /**
     *
     */
    @Autowired
    private ApiService apiService;

    /**
     *
     */
    @Autowired
    private BaseCacheDao cache;

    /**
     *
     */
    @Autowired
    private SaleOrderCache saleOrderCache;

    /**
     *
     */
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SaleOrderService saleOrderService;


    /**
     *
     */
    @Autowired
    private Executor executor;

    /**
     * @param saleOrderDto
     * @return
     */
    @PostMapping("edit")
    public SaleOrder edit(@ModelAttribute final SaleOrderDto saleOrderDto) {
        SaleOrder data = this.apiService.edit(saleOrderDto);
        return data;
    }

    @PostMapping("address")
    @Transactional(rollbackFor = Exception.class)
    public Address address(@RequestParam String address) {
        Address add = new Address();
        add.setAddress(address);
        return this.addressRepository.save(add);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("get")
//    @Timed(value = "api.counter.requests", histogram = true)
    public SaleOrderDto get(@RequestParam final Long id) throws Exception {

        if (id % 7 == 0) {
            throw new Exception("错了");
        }

        SaleOrder saleOrder = this.saleOrderService.getCached(id);
        SaleOrderDto saleOrderDto = new SaleOrderDto();
        BeanUtils.copyProperties(saleOrder, saleOrderDto, "PRODUCT");
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(saleOrder.getProduct(), productDto);
        saleOrderDto.setProduct(productDto);
        return saleOrderDto;
    }

    @Autowired
    HealthEndpoint healthEndpoint;

    @GetMapping("health")
    public Object health() {
        return healthEndpoint.invoke();
    }


}

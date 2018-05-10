package com.zsw.api;

import com.zsw.base.redis.dao.commons.BaseCacheDao;
import com.zsw.cache.Address;
import com.zsw.cache.AddressRepository;
import com.zsw.cache.SaleOrderCache;
import com.zsw.conf.base.saleorder.ProductDto;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.user.bean.SaleOrder;
import com.zsw.service.ApiService;
import com.zsw.service.saleorder.SaleOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     *
     */
    @Autowired
    private SaleOrderService saleOrderService;


    /**
     *
     */
    @Autowired
    private Executor executor;

    /**
     * @param saleOrderDto SaleOrderDto
     * @return SaleOrder
     */
    @PostMapping("edit")
    public SaleOrder edit(@ModelAttribute final SaleOrderDto saleOrderDto) {
        SaleOrder data = this.apiService.edit(saleOrderDto);
        return data;
    }

    /**
     * @param address address
     * @return Address
     */
    @PostMapping("address")
    @Transactional(rollbackFor = Exception.class)
    public Address address(@RequestParam final String address) {
        Address add = new Address();
        add.setAddress(address);
        return this.addressRepository.save(add);
    }

    /**
     * @param id id
     * @return SaleOrderDto SaleOrderDto
     * @throws Exception e
     */
    @GetMapping("get")
//    @Timed(value = "api.counter.requests", histogram = true)
    public SaleOrderDto get(@RequestParam final Long id) throws Exception {
        SaleOrder saleOrder = this.saleOrderService.getCached(id);
        SaleOrderDto saleOrderDto = new SaleOrderDto();
        BeanUtils.copyProperties(saleOrder, saleOrderDto, "PRODUCT");
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(saleOrder.getProduct(), productDto);
        saleOrderDto.setProduct(productDto);
        return saleOrderDto;
    }

    /**
     *
     */
    @Autowired
    private HealthEndpoint healthEndpoint;

    /**
     * @return health
     */
    @GetMapping("health")
    public Object health() {
        return healthEndpoint.invoke();
    }


}

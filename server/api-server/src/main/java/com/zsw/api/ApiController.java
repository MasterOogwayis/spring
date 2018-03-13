package com.zsw.api;

import com.zsw.base.redis.dao.commons.BaseCacheDao;
import com.zsw.cache.Address;
import com.zsw.cache.AddressRepository;
import com.zsw.cache.SaleOrderCache;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.bean.SaleOrder;
import com.zsw.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

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
     * @param saleOrderDto
     * @return
     */
    @PostMapping("edit")
    public SaleOrder edit(@ModelAttribute final SaleOrderDto saleOrderDto) {
        SaleOrder data = this.apiService.edit(saleOrderDto);
        return data;
    }

    @PostMapping("keys")
    public Object keys(@RequestParam String keys) {
        return this.saleOrderCache.keys(keys);
    }

    @PostMapping("address")
    @Transactional(rollbackFor = Exception.class)
    public Address address(@RequestParam String address) {
        Address add = new Address();
        add.setAddress(address);
        return this.addressRepository.saveOrUpdate(add);
    }

    @GetMapping("pttl")
    @Transactional(rollbackFor = Exception.class)
    public Long pttl(@RequestParam String key) {
        return this.cache.pttl(key, TimeUnit.MILLISECONDS);
    }


}

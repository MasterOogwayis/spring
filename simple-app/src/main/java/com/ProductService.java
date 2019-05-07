package com;

import com.zsw.orm.redis.dao.commons.BaseCacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2019/5/7 15:29
 **/
@Service
public class ProductService {

    public static final String KEY = "product";

    @Autowired
    BaseCacheDao cacheDao;


    @CachePut(value = KEY, key = "#product.getId()")
    public Object save(Product product) {
        return product;
    }

    @Cacheable(value = KEY, key = "#id")
    public Product get(Long id) {
        return (Product) this.cacheDao.get(KEY + ":" + id);
    }


    @Cacheable(value = "list", key = "#product.getId()")
    public Object saveCustom(Product product) {
        return product;
    }

    @Cacheable(value = "list", key = "#id")
    public Product getCustom(Long id) {
        return (Product) this.cacheDao.get("list:" + id);
    }





}

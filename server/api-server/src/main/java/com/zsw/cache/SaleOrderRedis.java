package com.zsw.cache;

import com.zsw.base.redis.dao.RedisDao;
import com.zsw.persistence.user.bean.SaleOrder;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * demo
 *
 * @author ZhangShaowei on 2018/3/8 13:39
 **/
@Component(SaleOrderRedis.BEAN_NAME)
public class SaleOrderRedis extends RedisDao<String, SaleOrder> implements SaleOrderCache {

    /**
     *
     */
    public static final String BEAN_NAME = "saleOrderRedis";

    /**
     * @param pattern
     * @return
     */
    @Override
    public Set<String> keys(final String pattern) {
        return this.redisTemplate.keys(pattern);
    }

}

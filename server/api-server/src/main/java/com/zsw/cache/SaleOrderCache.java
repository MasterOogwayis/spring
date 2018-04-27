package com.zsw.cache;

import com.zsw.base.redis.dao.commons.CacheDao;
import com.zsw.persistence.timedtask.bean.SaleOrder;

import java.util.Set;

/**
 * deom
 *
 * @author ZhangShaowei on 2018/3/8 13:38
 **/
public interface SaleOrderCache extends CacheDao<String, SaleOrder> {

    Set<String> keys(final String pattern);

}

package com.zsw.base.redis.dao.commons;

import java.util.concurrent.TimeUnit;


/**
 * 描      述    : 用于操作 实体类型的接口
 *
 * @param <T> Bean
 * @author : zhangshaowei
 */
public interface EntityCacheDao<T> extends CacheDao<String, T> {

    /**
     * @param t T
     */
    void save(T t);

    /**
     * @param t       T
     * @param timeout long
     * @param unit    TimeUnit
     */
    void save(T t, long timeout, TimeUnit unit);

    T get(Long id);

}

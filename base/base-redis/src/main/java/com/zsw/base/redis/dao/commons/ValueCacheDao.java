package com.zsw.base.redis.dao.commons;

import java.util.Set;

/**
 * 描      述    : 操作基本类型的接口
 *
 * @author : zhangshaowei
 * @param <K> key
 * @param <V> value
 */
public interface ValueCacheDao<K, V> extends CacheDao<K, V> {

    /**
     * key 自增 1
     *
     * @param key
     * @param number
     * @return
     */
    Long increment(K key, long number);

    /**
     * 描述：存在则增加 or 返回null
     *
     * @param key    key
     * @param number number
     * @return 不存在key则返回null
     * @author : zhangshaowei
     * @version : v1.0
     * @date : 2017年2月16日 下午2:51:32
     * @since : v1.0
     */
    Long existsIncr(K key, long number);

    /**
     * key 自减 1
     *
     * @param key
     * @param number
     * @return
     */
    Long decrement(K key, long number);

    /**
     * @param key
     * @param value
     * @return
     */
    Boolean setNX(K key, V value);

    /**
     * @param key
     * @param value
     * @return
     */
    V getAndSet(K key, V value);

    /**
     * @return
     */
    String ping();

    /**
     * @param pattern
     * @return
     */
    Set<K> keys(K pattern);

}

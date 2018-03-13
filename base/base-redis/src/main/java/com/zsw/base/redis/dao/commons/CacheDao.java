package com.zsw.base.redis.dao.commons;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 描      述    : cache 通用接口
 *
 * @author : zhangshaowei CopyRight (c) 2017 Company, Inc. All rights reserved.
 * @param <K> key
 * @param <V> value
 */
public interface CacheDao<K, V> {

    /**
     * @param key
     * @param value
     */
    void set(K key, V value);

    /**
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    void set(K key, V value, long timeout, TimeUnit unit);

    /**
     * @param key
     * @return
     */
    V get(K key);

    /**
     * @param key
     */
    void del(K key);

    /**
     * @param key
     * @param values
     */
    //List 相关
    void leftPushAll(K key, List<V> values);

    /**
     * @param key
     * @param values
     */
    void rightPushAll(K key, List<V> values);

    /**
     * @param key
     * @param value
     */
    void leftPush(K key, V value);

    /**
     * @param key
     * @param value
     */
    void rightPush(K key, V value);

    /**
     * @param key
     * @param index
     * @return
     */
    V get(K key, Long index);

    /**
     * @param key
     * @return
     */
    V leftPop(K key);

    /**
     * @param key
     * @return
     */
    V rightPop(K key);

    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<V> range(K key, long start, long end);

    /**
     * @param key
     * @return
     */
    Long size(K key);

    /**
     * @param key
     * @param hashKeys
     */
    //Hash相关
    @SuppressWarnings("unchecked")
    void hdel(K key, K... hashKeys);

    /**
     * @param key
     * @param hashKey
     * @return
     */
    Boolean hexists(K key, K hashKey);

    V hget(K key, K hashKey);

    /**
     * @param key
     * @return
     */
    Map<K, V> entries(K key);

    /**
     * @param key
     * @param map
     */
    void putAll(K key, Map<K, V> map);

    /**
     * @param key
     * @param hashKey
     * @param value
     */
    void put(K key, K hashKey, V value);

    /**
     * @param key
     * @return
     */
    Set<K> hkeys(K key);

    /**
     * @param key
     * @return
     */
    long pttl(K key);

    /**
     * @param key
     * @param timeUnit
     * @return
     */
    long pttl(K key, TimeUnit timeUnit);

    /**
     * @param key
     * @return
     */
    //其他
    Boolean exists(K key);

    /**
     *
     */
    void flushDb();

    /**
     *
     */
    void flushAll();

    /**
     * @param key
     * @param timeout
     * @param unit
     */
    void expire(K key, long timeout, TimeUnit unit);

}

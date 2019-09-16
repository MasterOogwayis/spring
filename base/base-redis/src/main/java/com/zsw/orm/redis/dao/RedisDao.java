package com.zsw.orm.redis.dao;

import com.zsw.orm.redis.dao.commons.CacheDao;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * 描述：基础操作实现类redis key-value cache
 * <p>
 * 包含主要数据类型操作  key-value list hash
 * 非常用数据结构暂无 扩充请参考spring-data-redis core
 *
 * @author zhangshaowei
 * @version v1.0
 * @since v1.0
 */
public class RedisDao<K, V> implements CacheDao<K, V> {

    /**
     *
     */
    @Resource(name = "redisTemplate")
    protected RedisTemplate<K, V> redisTemplate;

    /**
     *
     */
    @Resource(name = "redisTemplate")
    protected ValueOperations<K, V> valueOps;

    /**
     *
     */
    @Resource(name = "redisTemplate")
    protected ListOperations<K, V> listOps;

    /**
     *
     */
    @Resource(name = "redisTemplate")
    protected HashOperations<K, K, V> hashOps;


    /**
     * 描述：不设置缓存 失效时间是非常危险的 所以默认30分钟
     *
     * @param key   key
     * @param value V
     * @author : zhangshaowei
     * @since : v1.0
     */
    @Override
    public void set(K key, V value) {
        this.set(key, value, 30, TimeUnit.MINUTES);
    }


    /**
     * 描述：所有缓存必须设置过期时间
     *
     * @param key     键
     * @param value   值
     * @param timeout 有效时间
     * @param unit    时间类型
     * @author : zhangshaowei
     * @since : v1.0
     */
    @Override
    public void set(K key, V value, long timeout, TimeUnit unit) {
        this.valueOps.set(key, value, timeout, unit);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        return this.valueOps.get(key);
    }

    /**
     * @param key
     */
    @Override
    public void del(K key) {
        this.redisTemplate.delete(key);
    }

    /**
     * @param key
     * @param values
     */
    @Override
    public void leftPushAll(K key, List<V> values) {
        this.listOps.leftPushAll(key, values);
    }

    /**
     * 描述：rightPushAll能保证 range 取出集合与存入集合顺序相同
     *
     * @param key    key
     * @param values List
     * @author : zhangshaowei
     * @version : v1.0
     * @date : 2017年1月19日 下午2:28:26
     * @since : v1.0
     */
    @Override
    public void rightPushAll(K key, List<V> values) {
        this.listOps.rightPushAll(key, values);
    }

    /**
     * @param key
     * @param value
     */
    @Override
    public void leftPush(K key, V value) {
        this.listOps.leftPush(key, value);
    }

    /**
     * @param key
     * @param value
     */
    @Override
    public void rightPush(K key, V value) {
        this.listOps.rightPush(key, value);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public V rightPop(K key) {
        return this.listOps.rightPop(key);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public V leftPop(K key) {
        return this.listOps.leftPop(key);
    }

    /**
     * @param key
     * @param index
     * @return
     */
    @Override
    public V get(K key, Long index) {
        return this.listOps.index(key, index);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public Long size(K key) {
        return this.listOps.size(key);
    }


    /**
     * 描述：
     *
     * @param key   key
     * @param start 分页第一个元素
     * @param end   分页最后一个元素   查询所有传 -1
     * @return List
     * @author : zhangshaowei
     * @since : v1.0
     */
    @Override
    public List<V> range(K key, long start, long end) {
        return this.listOps.range(key, start, end);
    }


    /**
     * @param key
     * @param hashKeys
     */
    @Override
    public void hdel(K key, @SuppressWarnings("unchecked") K... hashKeys) {
        this.hashOps.delete(key, new Object[]{hashKeys});
    }

    /**
     * @param key
     * @param hashKey
     * @return
     */
    @Override
    public Boolean hexists(K key, K hashKey) {
        return this.hashOps.hasKey(key, hashKey);
    }

    /**
     * @param key
     * @param hashKey
     * @return
     */
    @Override
    public V hget(K key, K hashKey) {
        return this.hashOps.get(key, hashKey);
    }

    /**
     * @param key
     * @param map
     */
    @Override
    public void putAll(K key, Map<K, V> map) {
        this.hashOps.putAll(key, map);
    }

    /**
     * @param key
     * @param hashKey
     * @param value
     */
    @Override
    public void put(K key, K hashKey, V value) {
        this.hashOps.put(key, hashKey, value);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public Map<K, V> entries(K key) {
        return this.hashOps.entries(key);
    }


    /**
     * @param key
     * @return
     */
    @Override
    public Set<K> hkeys(K key) {
        return this.hashOps.keys(key);
    }


    /**
     * @param key
     * @return
     */
    @Override
    public Boolean exists(K key) {
        return this.redisTemplate.hasKey(key);
    }

    /**
     * 描述：清空缓存----当前缓存库
     *
     * @author : zhangshaowei
     * @since : v1.0
     */
    @Override
    public void flushDb() {
        throw new UnsupportedOperationException("not allow here......");
    }

    /**
     * 描述：清空缓存----包含所有集群的从库
     *
     * @author : zhangshaowei
     * @since : v1.0
     */
    @Override
    public void flushAll() {
        throw new UnsupportedOperationException("not allow here......");
    }


    /**
     * 描述：设置缓存过期时间，过期后自动删除----这个需要常用
     *
     * @param key     key
     * @param timeout Long
     * @param unit    TimeUnit
     * @author : zhangshaowei
     * @since : v1.0
     */
    @Override
    public void expire(K key, long timeout, TimeUnit unit) {
        this.redisTemplate.expire(key, timeout, unit);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public long pttl(K key) {
        return this.redisTemplate.getExpire(key);
    }

    @Override
    public long pttl(K key, TimeUnit timeUnit) {
        return this.redisTemplate.getExpire(key, timeUnit);
    }

}

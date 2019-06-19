package com.zsw.orm.redis.dao;

import com.zsw.orm.redis.dao.commons.BaseCacheDao;
import org.springframework.data.redis.connection.RedisConnectionCommands;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 描      述    : 操作缓存不开启事物   or 不能及时得到缓存数据反馈
 *
 * @author : zhangshaowei
 */
@Component
public class BaseRedisDao extends RedisDao<String, Object> implements BaseCacheDao {
    /**
     * repository name
     */
    public static final String REPOSITORY_NAME = "baseRedisCache";

    /**
     * 描述：开启事物后 redis的incr并不会立即返回
     * redis的事物是一队指令集
     * 只有在spring事物成功提交后才会提交运行
     *
     * @param key key
     * @return Long
     * @author : zhangshaowei
     * @version : v1.0
     * @date : 2017年2月10日 上午9:10:12
     * @since : v1.0
     */
    @Override
    public Long increment(final String key, final long number) {
        return this.valueOps.increment(key, number);
    }

    /**
     * @param key    key
     * @param number number
     * @return
     */
    @Override
    public Long existsIncr(final String key, final long number) {
        if (this.exists(key)) {
            return this.valueOps.increment(key, number);
        }
        return null;
    }

    /**
     * @param key
     * @param number
     * @return
     */
    @Override
    public Long decrement(final String key, final long number) {
        return this.valueOps.increment(key, -number);
    }


    /**
     * 描述：用于实现 分布式锁
     * 1. setNX true
     * 2. expire 设置超时时间
     * 其他线程or实例     1. setNx false
     * 2. 等待随机时间继续请求锁
     * 3. 超出等待时间则中断
     * PS : 当缓存服 gg 后被穿透 则采用直接锁数据库的方式
     * 然后尝试继续吧新数据写入缓存
     *
     * @param key   key
     * @param value value
     * @return boolean
     * @author : zhangshaowei
     * @version : v1.0
     * @date : 2017年1月11日 下午1:26:52
     * @since : v1.0
     */
    @Override
    public Boolean setNX(final String key, final Object value) {
        return this.valueOps.setIfAbsent(key, value);
    }

    /**
     * @param key     键
     * @param value   值
     * @param timeout 生存时间
     * @param unit    TimeUnit
     * @return
     */
    @Override
    public Boolean setNXExpire(final String key, final Object value, final Long timeout, final TimeUnit unit) {
        Boolean success = this.valueOps.setIfAbsent(key, value);
        if (success) {
            this.expire(key, timeout, unit);
        }
        return success;
    }


    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public Object getAndSet(final String key, final Object value) {
        return this.valueOps.getAndSet(key, value);
    }

    /**
     * @param pattern
     * @return
     */
    @Override
    public Set<String> keys(final String pattern) {
        return this.redisTemplate.keys(pattern);
    }

    /**
     * @return
     */
    @Override
    public String ping() {
        return this.redisTemplate.execute(RedisConnectionCommands::ping);
    }

    @Override
    public List<Object> multiGet(Set<String> keys) {
        return super.valueOps.multiGet(keys);
    }

}

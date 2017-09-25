package com.zsw.base.redis.dao.commons;


import java.util.concurrent.TimeUnit;

/**
 * 描      述    : 操作基本类型的接口
 *
 * @author : zhangshaowei
 */
public interface BaseCacheDao extends ValueCacheDao<String, Object> {


    /**
     * 描述：联合操作 setNx后设置 过期时间
     *
     * @param key     键
     * @param value   值
     * @param timeout 生存时间
     * @param unit    TimeUnit
     * @return 设定成功？
     * @author : zhangshaowei
     * @since : v1.0
     */
    Boolean setNXExpire(String key, Object value, Long timeout, TimeUnit unit);

}

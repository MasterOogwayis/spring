package org.springframework.data.redis.cache;

import lombok.AllArgsConstructor;

/**
 * @author ZhangShaowei on 2019/6/19 11:12
 **/
@AllArgsConstructor
public class RedisCacheWrapper {

    private RedisCache redisCache;


    public byte[] createAndConvertCacheKey(Object key) {
        return redisCache.serializeCacheKey(redisCache.createCacheKey(key));
    }

    public byte[] serializeCacheValue(Object value) {
        return redisCache.serializeCacheValue(value);
    }

}

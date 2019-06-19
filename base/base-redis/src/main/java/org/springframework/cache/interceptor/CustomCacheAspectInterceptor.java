package org.springframework.cache.interceptor;

import org.springframework.cache.Cache;
import org.springframework.cache.support.NullValue;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheWrapper;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/6/19 9:57
 **/
public class CustomCacheAspectInterceptor extends CacheInterceptor {

    @Override
    protected void doPut(Cache cache, Object key, @Nullable Object result) {
        try {
            cache.put(key, result);
        } catch (RuntimeException ex) {
            getErrorHandler().handleCachePutError(ex, cache, key, result);
        }
    }


    private class CachePutRequest {

        private final CacheOperationContext context;

        private final Object key;

        public CachePutRequest(CacheOperationContext context, Object key) {
            this.context = context;
            this.key = key;
        }

        public void apply(@Nullable Object result) {
            if (this.context.canPutToCache(result)) {
                CacheOperation operation = context.getOperation();
                for (Cache cache : this.context.getCaches()) {
                    if (cache instanceof RedisCache && operation instanceof CustomCacheableOperation) {
                        CustomCacheableOperation customCacheableOperation = (CustomCacheableOperation) operation;
                        if (customCacheableOperation.getExpire() <= 0) {
                            doPut(cache, this.key, result);
                        } else {
                            RedisCache redisCache = (RedisCache) cache;
                            try {
                                redisCache.put(key, result);
                                Object cacheValue = result;
                                if (Objects.isNull(cacheValue)) {
                                    cacheValue = redisCache.isAllowNullValues() ? NullValue.INSTANCE : null;
                                }

                                if (!redisCache.isAllowNullValues() && cacheValue == null) {

                                    throw new IllegalArgumentException(String.format(
                                            "Cache '%s' does not allow 'null' values. Avoid storing null via '@Cacheable(unless=\"#result == null\")' or configure RedisCache to allow 'null' via RedisCacheConfiguration.",
                                            redisCache.getName()));
                                }
                                TimeUnit timeUnit = customCacheableOperation.getTimeUnit();
                                long expire = customCacheableOperation.getExpire();
                                RedisCacheWrapper wrapper = new RedisCacheWrapper(redisCache);
                                byte[] keySeri = wrapper.createAndConvertCacheKey(key);
                                byte[] valueSeri = wrapper.serializeCacheValue(cacheValue);
                                redisCache.getNativeCache().put(
                                        redisCache.getName(),
                                        keySeri,
                                        valueSeri,
                                        Duration.ofSeconds(timeUnit.toSeconds(expire)));
                            } catch (RuntimeException ex) {
                                getErrorHandler().handleCachePutError(ex, cache, key, result);
                            }
                        }
                    } else {
                        doPut(cache, this.key, result);
                    }
                }
            }
        }
    }

}

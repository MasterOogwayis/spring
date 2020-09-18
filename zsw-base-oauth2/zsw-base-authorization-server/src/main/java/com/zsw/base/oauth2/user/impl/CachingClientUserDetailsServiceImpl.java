package com.zsw.base.oauth2.user.impl;

import com.zsw.base.oauth2.ClientUserDetailsService;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 验证过后将用户信息放入缓存
 *
 * @author ZhangShaowei on 2020/7/3 14:05
 */
public class CachingClientUserDetailsServiceImpl extends CompositeClientUserDetailsServiceImpl {

    private final ValueOperations<String, Object> cache;

    public CachingClientUserDetailsServiceImpl(List<ClientUserDetailsService> services, ValueOperations<String, Object> cache) {
        super(services);
        this.cache = cache;
    }


    @Override
    public void save(String username, String clientId, Object attr, int expire) {
        this.cache.set(this.cacheKey(username, clientId), attr, expire, TimeUnit.SECONDS);
    }
}

package com.zsw.base.oauth2.resource;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 验证过后将用户信息放入缓存
 *
 * @author ZhangShaowei on 2020/7/3 14:05
 */
@AllArgsConstructor
public class CachingClientUserDetailsServiceImpl implements ResourceUserDetailsService {

    /**
     *
     */
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public ResourceUserDetails loadUserByUsername(String username, String clientId) throws UsernameNotFoundException {
        return new ResourceUserDetails(
                clientId,
                username,
                () -> {
                    //noinspection unchecked
                    return (Map<String, Object>) redisTemplate.opsForValue().get(this.cacheKey(username, clientId));
                }
        );
    }

    /**
     * 缓存时间为 token 有效时间，这里没办法动态设置所以放到统一的配置提前设置
     * ep:  SystemCacheStrategyImpl
     *
     * @param username username username
     * @param clientId clientId clientId
     * @param attr     附加信息
     * @param expire   超时时间  单位 秒
     */
    @Override
    public void save(String username, String clientId, Map<String, Object> attr, int expire) {
        this.redisTemplate.opsForValue().set(this.cacheKey(username, clientId), attr, expire, TimeUnit.SECONDS);
    }


    @Override
    public void logout(String username, String clientId) {
        this.redisTemplate.delete(this.cacheKey(username, clientId));
    }
}

package com.zsw.base.oauth2.resource;

import org.springframework.boot.actuate.autoconfigure.metrics.cache.CacheMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author ZhangShaowei on 2021/5/11 16:53
 */
@Configuration
@ConditionalOnBean(RedisTemplate.class)
@AutoConfigureAfter(CacheMetricsAutoConfiguration.class)
@ConditionalOnMissingBean(ResourceUserDetailsService.class)
public class ResourceUserDetailsAutoConfiguration {


    @Bean
    public ResourceUserDetailsService resourceUserDetailsService(RedisTemplate<String, Object> redisTemplate) {
        return new CachingClientUserDetailsServiceImpl(redisTemplate);
    }


}

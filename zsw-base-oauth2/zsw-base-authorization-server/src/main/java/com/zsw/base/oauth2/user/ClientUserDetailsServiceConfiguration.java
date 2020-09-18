package com.zsw.base.oauth2.user;

import com.zsw.base.oauth2.ClientUserDetailsService;
import com.zsw.base.oauth2.user.impl.CachingClientUserDetailsServiceImpl;
import com.zsw.base.oauth2.user.impl.CompositeClientUserDetailsServiceImpl;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2020/7/2 16:27
 */
@Configuration
@ConditionalOnBean(ClientUserDetailsService.class)
public class ClientUserDetailsServiceConfiguration {


    /**
     * @param servicesProvider ClientUserDetailsService
     * @return CompositeClientUserDetailsService
     */
    @Bean
    @Primary
    @ConditionalOnMissingClass("org.springframework.data.redis.core.RedisTemplate")
    public ClientUserDetailsService compositeClientUserDetailsService(
            ObjectProvider<ClientUserDetailsService> servicesProvider) {
        return servicesProvider.stream().collect(Collectors.collectingAndThen(Collectors.toList(), CompositeClientUserDetailsServiceImpl::new));
    }

    /**
     * 若有缓存则使用缓存保存用户信息
     *
     * @param servicesProvider ClientUserDetailsService
     * @param redisTemplate    redis support
     * @return ClientUserDetailsService
     */
    @Bean
    @Primary
    @ConditionalOnClass(RedisTemplate.class)
    public ClientUserDetailsService cachingClientUserDetailsService(
            ObjectProvider<ClientUserDetailsService> servicesProvider,
            RedisTemplate<String, Object> redisTemplate
    ) {
        return new CachingClientUserDetailsServiceImpl(
                servicesProvider.stream().collect(Collectors.toList()), redisTemplate.opsForValue());
    }


}

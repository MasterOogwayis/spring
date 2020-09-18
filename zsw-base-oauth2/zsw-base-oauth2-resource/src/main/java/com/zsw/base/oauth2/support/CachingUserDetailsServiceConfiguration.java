package com.zsw.base.oauth2.support;

import com.zsw.base.oauth2.ClientUserDetailsService;
import com.zsw.base.oauth2.ResourceServerProperties;
import com.zsw.base.oauth2.user.CachingResourceUserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

/**
 * @author ZhangShaowei on 2020/3/2 13:59
 */
@Configuration
@ConditionalOnClass(RedisTemplate.class)
@EnableConfigurationProperties(ResourceServerProperties.class)
public class CachingUserDetailsServiceConfiguration {

    @Bean
    public AccessTokenConverterCustomizer cacheAccessTokenConverterCustomizer(
            ClientUserDetailsService userDetailsService, ResourceServerProperties properties) {
        return converter -> {
            DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
            ClientUserAuthenticationConverter userTokenConverter = new ClientUserAuthenticationConverter();
            userTokenConverter.setUserDetailsService(userDetailsService);
            userTokenConverter.setValidate(properties.getValidateAdditionalInformation());
            accessTokenConverter.setUserTokenConverter(userTokenConverter);
        };
    }


    /**
     * @param redisTemplate RedisTemplate<String, Object>
     * @return UserDetailsService
     */
    @Bean
    @ConditionalOnMissingBean(ClientUserDetailsService.class)
    public ClientUserDetailsService userDetailsService(RedisTemplate<String, Object> redisTemplate) {
        return new CachingResourceUserDetailsServiceImpl(redisTemplate);
    }


}

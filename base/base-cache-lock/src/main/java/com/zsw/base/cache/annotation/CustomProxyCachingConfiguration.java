package com.zsw.base.cache.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.interceptor.CacheOperationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author ZhangShaowei on 2017/10/12 16:44
 */
//@Configuration
public class CustomProxyCachingConfiguration {

    /**
     * @return
     */
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public CacheOperationSource cacheOperationSource() {
        return new CustomAnnotationCacheOperationSource();
    }

}

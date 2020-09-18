package com.zsw.base.oauth2.client;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * FeignClient 客户端 token 传递的策略
 * 1. 优先使用请求自带的 token, 传递到调用链
 * 2. 如果请求 header 里面没有，则使用 client 模式的平台 token (只有定时任务、自发验证才会这样)
 * The type Client configuration.
 *
 * @author ZhangShaowei on 2017/5/12 12:19
 */

@Configuration
@ConditionalOnClass(RequestInterceptor.class)
public class ResourceFeignConfiguration {

    /**
     * 将请求中的 token 传递下去
     */
    @Bean
    @ConditionalOnBean(OAuth2RestTemplate.class)
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
        return new Oauth2FeignRequestInterceptor(oAuth2RestTemplate);
    }

}

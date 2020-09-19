package com.zsw.base.oauth2.client;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * FeignClient 客户端 token 传递的策略
 * 和 resource 不同，认证服务器直接使用 平台级的 token
 *
 * @author ZhangShaowei on 2020-9-16 10:23:36
 */

@Configuration
@ConditionalOnClass(RequestInterceptor.class)
public class AuthorizationServerFeignConfiguration {

    /**
     * 将请求中的 token 传递下去
     */
    @Bean
    @ConditionalOnBean(OAuth2RestTemplate.class)
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
        return new Oauth2FeignRequestInterceptor(oAuth2RestTemplate);
    }

}

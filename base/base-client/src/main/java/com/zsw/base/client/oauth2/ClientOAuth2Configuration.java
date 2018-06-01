package com.zsw.base.client.oauth2;

import feign.RequestInterceptor;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * ClientOAuth2Configuration
 *
 * @author ZhangShaowei on 2018/6/1 11:17
 **/
@Configuration
public class ClientOAuth2Configuration {


    @Bean
    public RequestInterceptor requestInterceptor(
            OAuth2ClientContext oAuth2ClientContext, OAuth2ProtectedResourceDetails details) {
        return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, details);
    }


}

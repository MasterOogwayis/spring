package com;

import feign.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * SaleOrderApplication
 *
 * @author ZhangShaowei on 2018/6/1 11:02
 **/
@SpringBootApplication
public class SaleOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleOrderApplication.class, args);
    }


    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
        return template -> {
            OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();
            template.header(HttpHeaders.AUTHORIZATION, accessToken.getTokenType() + " " + accessToken.getValue());
        };
    }


}

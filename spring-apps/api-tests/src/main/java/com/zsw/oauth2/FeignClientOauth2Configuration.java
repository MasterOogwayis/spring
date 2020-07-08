//package com.zsw.oauth2;
//
//import feign.RequestInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//
///**
// * @author ZhangShaowei on 2020/7/8 9:14
// */
//@Configuration
//public class FeignClientOauth2Configuration {
//
//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
//        return template -> {
//            OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();
//            template.header(HttpHeaders.AUTHORIZATION, accessToken.getTokenType() + " " + accessToken.getValue());
//        };
//    }
//
//}

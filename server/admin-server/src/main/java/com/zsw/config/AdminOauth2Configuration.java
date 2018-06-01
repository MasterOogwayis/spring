package com.zsw.config;

import de.codecentric.boot.admin.web.client.HttpHeadersProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * AdminServerConfiguration 如果 微服务使用了 oauth2 协议 admin需要开启次配置 并引入基础支持包 oauth2-client
 *
 * @author ZhangShaowei on 2018/5/28 9:59
 **/
@Configuration
public class AdminOauth2Configuration {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AdminOauth2Configuration.class);

    /**
     * cloud 服务之间使用了 oauth2 协议
     * 在admin 发送到所有服务请求 headers 中添加 权限 Authorization 类型 默认 Bearer
     *
     * @param oAuth2RestTemplate
     * @return
     */
    @Bean
    public HttpHeadersProvider httpHeadersProvider(OAuth2RestTemplate oAuth2RestTemplate) {
        return new OAuth2AuthorizationHttpHeadersProvider(oAuth2RestTemplate::getAccessToken);
    }


}

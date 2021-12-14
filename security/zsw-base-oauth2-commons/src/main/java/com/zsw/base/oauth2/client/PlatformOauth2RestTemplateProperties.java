package com.zsw.base.oauth2.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ZhangShaowei on 2020/9/14 13:26
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.security.oauth2.platform")
public class PlatformOauth2RestTemplateProperties {

    private String authorizationServer;

    private String clientId;

    private String clientSecret;

}

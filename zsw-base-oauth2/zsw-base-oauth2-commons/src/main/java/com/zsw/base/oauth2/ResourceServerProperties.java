package com.zsw.base.oauth2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ZhangShaowei on 2020/7/2 10:27
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.security.oauth2.resource-server")
public class ResourceServerProperties {

    private String resourceId;

    private String verifierKeyFile;

    private String signingKey;

    /**
     * 是否验证 token
     * 验证缓存是否有信息，没有则 token 失效
     * 对比 token 中和 缓存中的时间戳，如果不相同则此 token 也失效
     */
    private Boolean validateAdditionalInformation = false;


}

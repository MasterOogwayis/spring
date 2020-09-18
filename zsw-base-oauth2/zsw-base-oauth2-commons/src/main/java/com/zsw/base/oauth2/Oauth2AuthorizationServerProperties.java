package com.zsw.base.oauth2;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ZhangShaowei on 2020/7/2 10:27
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.security.oauth2.authentication-server")
public class Oauth2AuthorizationServerProperties {

    private final JwtKeyStoreConfig jwt = new JwtKeyStoreConfig();

    @Data
    public static class JwtKeyStoreConfig {
        private String keyStore;

        private String keyStorePwd;

        private String keyPair;
    }


}

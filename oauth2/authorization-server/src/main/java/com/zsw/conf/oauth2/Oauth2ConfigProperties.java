package com.zsw.conf.oauth2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ZhangShaowei on 2019/8/29 10:51
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "com.zsw.oauth2")
public class Oauth2ConfigProperties {

    /**
     *
     */
    private String keyStoreFile;
    /**
     *
     */
    private String keyStorePwd;
    /**
     *
     */
    private String keyPair;

}

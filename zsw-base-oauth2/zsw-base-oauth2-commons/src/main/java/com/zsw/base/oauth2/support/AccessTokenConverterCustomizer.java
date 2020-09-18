package com.zsw.base.oauth2.support;

import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * 可以介入 JwtAccessTokenConverter 构建过程
 *
 * @author ZhangShaowei on 2020/3/2 14:53
 */
public interface AccessTokenConverterCustomizer {

    /**
     * 定制 JwtAccessTokenConverter
     *
     * @param converter JwtAccessTokenConverter
     */
    void customize(JwtAccessTokenConverter converter);

}

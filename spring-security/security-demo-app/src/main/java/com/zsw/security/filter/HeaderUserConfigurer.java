package com.zsw.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Authorization: Username base64(username)
 *
 * @author ZhangShaowei on 2021/12/15 11:49
 */
public class HeaderUserConfigurer<B extends HttpSecurityBuilder<B>> extends
        AbstractHttpConfigurer<HttpBasicConfigurer<B>, B> {

    @Override
    public void configure(B http) {
        AuthenticationManager authenticationManager = http
                .getSharedObject(AuthenticationManager.class);
        HeaderUserAuthenticationFilter authenticationFilter = new HeaderUserAuthenticationFilter(
                authenticationManager);
        authenticationFilter = postProcess(authenticationFilter);
        http.addFilterBefore(authenticationFilter, BasicAuthenticationFilter.class);
    }
}

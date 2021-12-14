package com.zsw.base.oauth2.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * 将请求中的 token 传递到 OpenFeign 调用链
 *
 * @author ZhangShaowei on 2020/9/14 10:34
 */
@AllArgsConstructor
public class Oauth2FeignRequestInterceptor implements RequestInterceptor {

    private final OAuth2RestTemplate oAuth2RestTemplate;

    /**
     * The name of the token.
     */
    public static final String BEARER = "Bearer";

    /**
     * The name of the header.
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * Create a template with the header of provided name and extracted extract.
     *
     * @see RequestInterceptor#apply(RequestTemplate)
     */
    @Override
    public void apply(RequestTemplate template) {
        // Clears out the header, no "clear" method available.
        template.header(AUTHORIZATION);
        template.header(AUTHORIZATION, getToken());
    }

    /**
     * bearer token
     * <p>
     * 1. 优先使用请求自带的token,传递到调用链
     * 2. 请求中找不到则使用平台级的 token
     *
     * @return valid token
     */
    public String getToken() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .map(request -> request.getHeader(AUTHORIZATION))
                .orElseGet(() -> {
                    OAuth2AccessToken accessToken = Oauth2FeignRequestInterceptor.this.oAuth2RestTemplate.getAccessToken();
                    return accessToken.getTokenType() + " " + accessToken.getValue();
                });
    }



}

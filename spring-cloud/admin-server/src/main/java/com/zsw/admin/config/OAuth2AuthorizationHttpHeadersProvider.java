package com.zsw.admin.config;

/**
 * 默认 admin 发送到其他客户端的 provider 是 BasicAuthHttpHeaderProvider - username & password
 * <p>
 * 定义一个 Oauth2 模式的 provider
 * <p>
 * OAuth2AuthorizationHttpHeadersProvider
 *
 * @author ZhangShaowei on 2018/5/28 9:48
 **/
public class OAuth2AuthorizationHttpHeadersProvider {
    // implements HttpHeadersProvider
//    public OAuth2AuthorizationHttpHeadersProvider(Supplier<OAuth2AccessToken> supplier) {
//        this.supplier = supplier;
//    }
//
//    private Supplier<OAuth2AccessToken> supplier;
//
//    @Override
//    public HttpHeaders getHeaders(Application application) {
//        OAuth2AccessToken accessToken = this.supplier.get();
//        String tokenType = accessToken.getTokenType();
//        if (!StringUtils.hasText(tokenType)) {
//            // 默认 Bearer
//            tokenType = OAuth2AccessToken.BEARER_TYPE;
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.AUTHORIZATION, tokenType + " " + accessToken.getValue());
//        return headers;
//    }
}

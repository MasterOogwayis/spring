package com.zsw.oauth2;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.DefaultUserInfoRestTemplateFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2020/7/8 9:13
 */
@Configuration
public class OAuth2RestTemplateConfiguration {

    @Value("${anze.base.application.servername.oauth}")
    private String oauthServerName;

    @Value("${anze.base.oauthclient.authorization}")
    private String authorization;


    /**
     * 生成 OAuth2RestTemplate
     * <p>
     * 注意这个 OAuth2RestTemplate 可以替代 RestTemplate 在服务之间工作，所有请求都会默认带上 Authorization: Bearer XXX
     * 但是它不能通过域名访问服务之外的资源了，官网建议的是 使用2套RestTemplate，一套对内
     * 另一套 new RestTemplate(),提供对外服务
     *
     * @param userInfoRestTemplateFactory UserInfoRestTemplateFactory
     * @return OAuth2RestTemplate
     * @see this#userInfoRestTemplateCustomizer 配置了 LoadBalancerInterceptor 所以不需要 @LoadBalance
     */
    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(UserInfoRestTemplateFactory userInfoRestTemplateFactory) {
        return userInfoRestTemplateFactory.getUserInfoRestTemplate();
    }

    /**
     * 定义认证资源
     * <p>
     * 4种模式都有对应的实现
     *
     * @return OAuth2ProtectedResourceDetails
     */
    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
        String decode = new String(Base64.getDecoder().decode(this.authorization), StandardCharsets.UTF_8);
        String[] split = decode.split(":");
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri("http://" + this.oauthServerName + "/oauth/token");
        resource.setClientId(split[0]);
        resource.setClientSecret(split[1]);
        //注意这里只能使用 header 模式，token server 有权限认证，使用 Header 走 BasicAuthenticationFilter
        resource.setClientAuthenticationScheme(AuthenticationScheme.header);
        return resource;
    }


    /**
     * 注入 UserInfoRestTemplateCustomizer , OAuth2ProtectedResourceDetails , OAuth2ClientContext
     * 没有用到 Sso 相关的，所以需要手动配置
     *
     * @param customizers         UserInfoRestTemplateCustomizer
     * @param details             资源配置
     * @param oauth2ClientContext 维护 accessToken
     * @return UserInfoRestTemplateFactory
     */
    @Bean
    public UserInfoRestTemplateFactory userInfoRestTemplateFactory(
            ObjectProvider<List<UserInfoRestTemplateCustomizer>> customizers,
            ObjectProvider<OAuth2ProtectedResourceDetails> details,
            ObjectProvider<OAuth2ClientContext> oauth2ClientContext) {
        return new DefaultUserInfoRestTemplateFactory(customizers, details, oauth2ClientContext);
    }


    /**
     * 这里配置的 accessToken server 地址 使用了微服务中的 服务名称 所以需要 配置 LoadBalancerInterceptor 解析
     * 和 RestTemplate 上面 @Loadbalance 功能相同，但是目前最新的 G 版不支持对 OAuth2RestTemplate 的负载注解，所以需要手动配置
     * *** 若使用了 spring-retry 这里 是无法注入 LoadBalancerInterceptor ***
     * <p>
     * {@link org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration}
     * {@link UserInfoRestTemplateFactory}
     *
     * @param loadBalancerInterceptor LoadBalancerInterceptor
     * @return UserInfoRestTemplateCustomizer
     */
    @Bean
    public UserInfoRestTemplateCustomizer userInfoRestTemplateCustomizer(LoadBalancerInterceptor loadBalancerInterceptor) {
        return template -> {
            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
            interceptors.add(loadBalancerInterceptor);
            // 需要支持所有4种认证模式
            AccessTokenProviderChain accessTokenProviderChain = Stream.of(
                    new AuthorizationCodeAccessTokenProvider(),
                    new ImplicitAccessTokenProvider(),
                    new ResourceOwnerPasswordAccessTokenProvider(),
                    new ClientCredentialsAccessTokenProvider()
            )
                    .peek(tp -> tp.setInterceptors(interceptors))
                    .collect(Collectors.collectingAndThen(Collectors.toList(), AccessTokenProviderChain::new));
            template.setInterceptors(interceptors);
            template.setAccessTokenProvider(accessTokenProviderChain);
        };
    }


}

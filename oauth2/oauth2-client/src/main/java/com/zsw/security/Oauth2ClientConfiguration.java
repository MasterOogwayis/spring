package com.zsw.security;

import lombok.Setter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.security.oauth2.resource.DefaultUserInfoRestTemplateFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 配置 客户端 oauth2 协议 以便使用 resource 资源服务器
 * <p>
 * AdminServerConfiguration
 *
 * @author ZhangShaowei on 2018/5/28 9:59
 **/
@Setter
@Configuration
@ConfigurationProperties(prefix = "security.oauth2.client")
public class Oauth2ClientConfiguration {

//    /**
//     * logger
//     */
//    private static final Logger logger = LoggerFactory.getLogger(Oauth2ClientConfiguration.class);


    /**
     *
     */
    private String clientId;

    /**
     *
     */
    private String clientSecret;

    /**
     *
     */
    private String accessTokenUri;

    @Bean
    public UserInfoRestTemplateFactory userInfoRestTemplateFactory(
            ObjectProvider<List<UserInfoRestTemplateCustomizer>> customizers,
            ObjectProvider<OAuth2ProtectedResourceDetails> details,
            ObjectProvider<OAuth2ClientContext> oauth2ClientContext) {
        return new DefaultUserInfoRestTemplateFactory(customizers, details, oauth2ClientContext);
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestOperations(UserInfoRestTemplateFactory userInfoRestTemplateFactory) {
        return userInfoRestTemplateFactory.getUserInfoRestTemplate();
    }


    /**
     * 无需 自定义 new OAuth2RestTemplete()
     * {@link org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration}
     * <p>
     * {@link UserInfoRestTemplateFactory}
     * 注入 UserInfoRestTemplateCustomizer , OAuth2ProtectedResourceDetails , OAuth2ClientContext
     * <p>
     * 这里配置的 accessToken server 地址 使用了微服务中的 服务名称 所以需要 配置 LoadBalancerInterceptor 解析
     * <p>
     * 若使用了 spring-retry 这里 是无法注入 LoadBalancerInterceptor
     *
     * @param loadBalancerInterceptor LoadBalancerInterceptor
     * @return UserInfoRestTemplateCustomizer
     */
    @Bean
    UserInfoRestTemplateCustomizer userInfoRestTemplateCustomizer(LoadBalancerInterceptor loadBalancerInterceptor) {
        return template -> {
            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
            interceptors.add(loadBalancerInterceptor);
            AccessTokenProviderChain accessTokenProviderChain = Stream
                    .of(new AuthorizationCodeAccessTokenProvider(), new ImplicitAccessTokenProvider(),
                            new ResourceOwnerPasswordAccessTokenProvider(), new ClientCredentialsAccessTokenProvider())
                    .peek(tp -> tp.setInterceptors(interceptors))
                    .collect(Collectors.collectingAndThen(Collectors.toList(), AccessTokenProviderChain::new));
            template.setAccessTokenProvider(accessTokenProviderChain);
        };
    }


    /**
     * {@link this#userInfoRestTemplateCustomizer(LoadBalancerInterceptor)}
     *
     * @return OAuth2ProtectedResourceDetails
     */
//    @Bean
//    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
//        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
//
//        resource.setAccessTokenUri(this.accessTokenUri);
//        resource.setClientId(this.clientId);
//        resource.setClientSecret(this.clientSecret);
////        resource.setGrantType("client_credentials"); default
//        // Authorization Basic Base64.getEncoder().encoder(clientId + ":" + clientSecret)
//        resource.setClientAuthenticationScheme(AuthenticationScheme.header);
//        return resource;
//    }

//    @Bean
//    public OAuth2ClientContext oAuth2ClientContext() {
//        return new DefaultOAuth2ClientContext();
//    }


}

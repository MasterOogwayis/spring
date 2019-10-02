package com.zsw.security.oauth2.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.concurrent.TimeUnit;

/**
 * spring cloud security oauth2
 * authorization_code 模式有2个巨大的坑：
 * 1. org.springframework.security.oauth2.core.OAuth2AuthenticationException: [authorization_request_not_found]
 *     授权中心和客户端都是采用相同地址，如localhost主机名，对于浏览器来说同一个域名，
 *    导致jsessionId在跳转到授权中心前的值和授权完毕后跳转回来的值不一致，浏览器认为是同一个域名所以JSessionID会被覆盖，
 *    导致在客户端从session中获取保存的 authorizationRequest 时获取不到，authorizcationRequest存放在session的Attribute中，
 *    key为JSessionID; 处理该问题方法就是让他们两个处于不同的域名或者ip，可以修改hosts增加一个域名。
 *
 * 2. org.springframework.security.oauth2.core.OAuth2AuthenticationException: [invalid_user_info_response] An error occurred while attempting to retrieve the UserInfo Resource: 401 null
 *   该问题是在OAuth2Client程序获取到授权中心回调的code后调用/oauth/token获取access_token后，
 *   紧接着调用userInfoUri获取用户认证信息(restTemplate)，此时传递的是头信息包含Authentication: Bearer xxxxxxxx(access_token），
 *   但是授权中心获取到该请求后没有使用OAuth2过滤器认证(由于没有配置EnableResourceServer导致该过滤器没被启用),
 *   而是通过session获取认证信息，但是由于没有携带session信息导致返回401未授权错误，
 *   出现上述问题。解决方法就是定义EnableResourceServer如下：并且注意下面注释部分的代码说明
 *
 *  EnableResourceServer注解会启用OAuth2的token认证，在原基础上增加OAuth2AuthenticationProcessingFilter过滤器
 *   EnableResourceServer->(import ResourceServerConfiguration)->configure(http)->ResourceServerSecurityConfigurer
 *   -> OAuth2AuthenticationProcessingFilter
 * @author ZhangShaowei on 2019/10/2 15:09
 **/
@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("sso_client").secret(this.passwordEncoder.encode("secret"))
                .redirectUris("http://localhost:8080/login")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("scope")
                .accessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(30))
                .and()
                .withClient("password_client").secret(this.passwordEncoder.encode("secret"))
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(30));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager)
                .tokenStore(this.tokenStore());
    }


    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}

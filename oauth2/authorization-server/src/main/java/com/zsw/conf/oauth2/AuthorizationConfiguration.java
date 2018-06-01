package com.zsw.conf.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2017/12/26 15:04
 */
@EnableAuthorizationServer
@Configuration
@ConfigurationProperties(prefix = "com.zsw.oauth2")
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

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

    /**
     *
     */
//    @Autowired
//    private UserDetailsService userDetailsService;

    /**
     *
     */
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    /**
     *
     */
    @Autowired
    private DataSource dataSource;


    /**
     * @TODO
     *
     * 支持 sso 登录   需要 security 的功能  使用 security client 还是 service
     */
//    @Autowired
//    private UserDetailsService myUserDetailsService;

    /**
     * ClientDetails实现
     *
     * @return
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(this.dataSource);
    }


    /**
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(this.dataSource);
//        clients.inMemory().withClient("otherserver").secret("123456789")
//                .authorizedGrantTypes("authorization_code", "refresh_token", "client_credentials").scopes("scope");
    }

    /**
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager)
                .tokenServices(this.tokenServices())
                .accessTokenConverter(this.accessTokenConverter());
//                .userDetailsService(this.userDetailsService);
    }


    /**
     * @return
     */
    @Bean
    @Primary
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setReuseRefreshToken(false);
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        defaultTokenServices.setClientDetailsService(clientDetails());
        return defaultTokenServices;
    }


    /**
     * token store
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 生成秘钥公钥
     * 秘钥由认证服务器保留
     * 公钥分配给资源服务器
     *
     * https://stackoverflow.com/questions/32867898/generate-private-and-public-key-file-using-keytool
     * token converter
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter  accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(new FileSystemResource(this.keyStoreFile), this.keyStorePwd.toCharArray())
                .getKeyPair(this.keyPair);
//                new JwtAccessTokenConverter() {
//            /***
//             * 重写增强token方法,用于自定义一些token返回的信息
//             */
//            @Override
//            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//                String userName = authentication.getUserAuthentication().getName();
//                user user = (user) authentication.getUserAuthentication().getPrincipal();// 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
//                /** 自定义一些token属性 ***/
//                final Map<String, Object> additionalInformation = new HashMap<>();
//                additionalInformation.put("userName", userName);
//                additionalInformation.put("roles", user.getAuthorities());
//                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
//                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
//                return enhancedToken;
//            }
//
//        };
        accessTokenConverter.setKeyPair(keyPair);
        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
//        accessTokenConverter.setSigningKey("123");
        return accessTokenConverter;
    }




    /**  */
    public void setKeyStoreFile(String keyStoreFile) {
        this.keyStoreFile = keyStoreFile;
    }

    /**  */
    public void setKeyStorePwd(String keyStorePwd) {
        this.keyStorePwd = keyStorePwd;
    }

    /**  */
    public void setKeyPair(String keyPair) {
        this.keyPair = keyPair;
    }

    /**  */
    public String getKeyStoreFile() {
        return keyStoreFile;
    }

    /**  */
    public String getKeyStorePwd() {
        return keyStorePwd;
    }

    /**  */
    public String getKeyPair() {
        return keyPair;
    }
}

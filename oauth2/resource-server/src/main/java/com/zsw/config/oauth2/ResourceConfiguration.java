package com.zsw.config.oauth2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * ResourceConfiguration
 *
 * @author ZhangShaowei on 2018/5/14 9:25
 **/
@Configuration
@EnableResourceServer
@ConfigurationProperties(prefix = "com.zsw.resource")
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {

    /**
     *
     */
    private String resourceId;

    /**
     *
     */
    private String verifierKeyFile;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(this.resourceId).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                // Since we want the protected resources to be accessible in the UI as well we need
                // session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                    .and()
//                    .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests().anyRequest().authenticated() //必须认证过后才可以访问
                .and()
                .csrf().disable();
        // @formatter:on
    }

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * token converter
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        Resource resource = new FileSystemResource(verifierKeyFile);
        String publicKey;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SignatureVerifier rsaVerifier = new RsaVerifier(publicKey);
        accessTokenConverter.setVerifier(rsaVerifier);
        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
//        accessTokenConverter.setSigningKey("123");
        return accessTokenConverter;
    }

    /**  */
    public String getResourceId() {
        return resourceId;
    }

    /**  */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**  */
    public String getVerifierKeyFile() {
        return verifierKeyFile;
    }

    /**  */
    public void setVerifierKeyFile(String verifierKeyFile) {
        this.verifierKeyFile = verifierKeyFile;
    }
}

package com.zsw.base.oauth2.config;

import com.zsw.base.oauth2.AuthorizationServerProperties;
import com.zsw.base.oauth2.resource.ResourceServerProperties;
import com.zsw.base.oauth2.resource.ResourceUserDetailsAutoConfiguration;
import com.zsw.base.oauth2.resource.ResourceUserDetailsService;
import com.zsw.base.oauth2.support.AccessTokenConverterCustomizer;
import com.zsw.base.oauth2.support.AdditionalAccessTokenConverter;
import com.zsw.base.oauth2.support.ClientUserAuthenticationConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author ZhangShaowei on 2020/7/2 16:03
 */
@AutoConfigureAfter(ResourceUserDetailsAutoConfiguration.class)
@Configuration
@EnableConfigurationProperties({AuthorizationServerProperties.class, ResourceServerProperties.class})
public class AccessTokenConverterConfiguration {

    @Autowired(required = false)
    private ResourceUserDetailsService resourceUserDetailsService;

    @Autowired
    private AuthorizationServerProperties properties;

    @Autowired
    private ResourceServerProperties resourceProperties;

    @Autowired(required = false)
    private ObjectProvider<AccessTokenConverterCustomizer> objectProvider;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // jwt token
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(
                        new FileSystemResource(this.properties.getJwt().getKeyStore()),
                        this.properties.getJwt().getKeyStorePwd().toCharArray()
                );
        // 自定义 UserDetailsService
        JwtAccessTokenConverter converter = new AdditionalAccessTokenConverter(resourceUserDetailsService);
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(this.properties.getJwt().getKeyPair()));
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        ClientUserAuthenticationConverter userAuthenticationConverter = new ClientUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(resourceUserDetailsService);
        userAuthenticationConverter.setValidate(resourceProperties.getValidateAdditionalInformation());
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        converter.setAccessTokenConverter(accessTokenConverter);
        // custom
        objectProvider.forEach(customizer -> customizer.customize(converter));
        return converter;
    }

}

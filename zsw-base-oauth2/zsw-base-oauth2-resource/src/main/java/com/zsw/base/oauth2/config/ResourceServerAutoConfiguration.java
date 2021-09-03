package com.zsw.base.oauth2.config;

import com.zsw.base.oauth2.SecurityProperties;
import com.zsw.base.oauth2.resource.ResourceServerProperties;
import com.zsw.base.oauth2.resource.ResourceUserDetailsAutoConfiguration;
import com.zsw.base.oauth2.userinfo.DefaultUserServiceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

/**
 * @author ZhangShaowei on 2020/9/11 14:39
 */
@Slf4j
@Configuration
@ConditionalOnBean(ResourceServerMakerConfiguration.Marker.class)
@Import({
        PasswordEncoderConfig.class,
        WebSecurityConfiguration.class,
        ResourceUserDetailsAutoConfiguration.class,
        DefaultJwtConverterConfiguration.class,
        DefaultUserServiceConfiguration.class
})
@EnableConfigurationProperties({ResourceServerProperties.class, SecurityProperties.class})
public class ResourceServerAutoConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerProperties resourcesProperties;

    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

//    @Autowired
//    private ResourceAccessDecisionManager accessDecisionManager;
//
//    @Autowired
//    private ResourceFilterInvocationSecurityMetadataSource securityMetadataSource;


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] ignored = Optional.ofNullable(this.securityProperties.ignored())
                .filter(list -> !CollectionUtils.isEmpty(list))
                .map(list -> list.toArray(new String[]{}))
                .orElseGet(() -> new String[]{});
        http.authorizeRequests()
                // 详细的角色资源权限控制
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
//                        object.setAccessDecisionManager(accessDecisionManager);
//                        object.setSecurityMetadataSource(securityMetadataSource);
//                        return object;
//                    }
//                })
                .antMatchers(ignored).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        log.info("Configuring ResourceServerSecurityConfigurer ");
        resources.resourceId(resourcesProperties.getResourceId()).tokenStore(tokenStore());
    }

}

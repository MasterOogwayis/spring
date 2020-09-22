package com.zsw.base.oauth2.config;

import com.zsw.base.oauth2.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author ZhangShaowei on 2019/8/19 11:37
 **/
@Configuration
@ConditionalOnBean(ResourceServerMakerConfiguration.Marker.class)
@EnableConfigurationProperties(SecurityProperties.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(this.securityProperties.ignored().toArray(new String[]{}));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                // FIXME sring cloud oauth2 已经弃用，不就将切换到 spring security 5.x
                // https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide
//                .and()
//                .oauth2ResourceServer()
                .and().csrf().disable().anonymous().disable();
        // @formatter:on
    }
}

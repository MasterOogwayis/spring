package com;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2017/12/26 9:37
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin");
    }


    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http //.antMatcher("/***")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable().anonymous().disable()
                .logout().logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/")
                .and()
                .httpBasic();
    }


}

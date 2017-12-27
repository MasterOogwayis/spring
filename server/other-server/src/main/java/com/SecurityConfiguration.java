package com;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2017/12/26 9:37
 */
@Component
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    /**
     * @param httpSecurity
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/***")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .authorizeRequests().antMatchers("/", "/anon").permitAll()
                .and()
                .csrf().disable()
                .logout().logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/");
    }


}

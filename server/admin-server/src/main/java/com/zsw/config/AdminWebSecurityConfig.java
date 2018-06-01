package com.zsw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * AdminWebSecurityConfig
 *
 * @author ZhangShaowei on 2018/5/25 16:15
 **/
@Configuration
@EnableWebSecurity
public class AdminWebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**", "/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .csrf().disable()
                .httpBasic();
    }





}

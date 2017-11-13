package com;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ZhangShaowei on 2017/11/13 11:06
 */
@Profile("secure")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 忽略css、img等文件
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html","/**.css", "/img/**", "/**.js","/third-party/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .and()
                .formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll().defaultSuccessUrl("/")
                .and()
                .logout()
                .deleteCookies("remove")
                .logoutSuccessUrl("/login.html").permitAll()
                .and()
                .httpBasic();

//        // Page with login form is served as /login.html and does a POST on /login
//        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
//        // The UI does a POST on /logout on logout
//        http.logout().logoutUrl("/logout");
//        // The ui currently doesn't support csrf
//        http.csrf().disable();
//
//        // Requests for the login page and the static assets are allowed
//        http.authorizeRequests()
//                .antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
//                .permitAll();
//        // ... and any other request needs to be authorized
//        http.authorizeRequests().antMatchers("/**").authenticated();
//
//        // Enable so that the clients can authenticate via HTTP basic for registering
//        http.httpBasic();
    }



}

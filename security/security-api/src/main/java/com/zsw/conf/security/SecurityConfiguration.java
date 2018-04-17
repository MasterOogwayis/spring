package com.zsw.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ZhangShaowei on 2017/9/21 14:43
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     *
     */
    @Autowired
    private UserDetailsService myUserDetailsService;

    /**
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
//        auth.eraseCredentials(false);
    }

    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login.html").loginProcessingUrl("/login").defaultSuccessUrl("/").permitAll()
                .and()
                .logout().deleteCookies("remove").invalidateHttpSession(false).logoutSuccessUrl("/")
                .and()
                .csrf().disable()
                .httpBasic();
//        http.authorizeRequests()
////                .antMatchers("/user").hasRole("ADMIN")
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .and().httpBasic()
//                .and()
//                .csrf().disable().anonymous().disable();

    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 忽略css、img等文件
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/commons/**");
    }

}

package com.zsw.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
        auth.userDetailsService(myUserDetailsService);
    }

    /**
     * 忽略css、img等文件
     *
     * @param web
     * @throws Exception
     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/**.html", "/**.css", "/img/**", "/**.js", "/third-party/**");
//    }


    /**
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/user").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .and()
                .csrf().disable().anonymous().disable();


//        http.csrf().disable().authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login.html").loginProcessingUrl("/login").defaultSuccessUrl("/").permitAll()
//                .and()
//                .logout().deleteCookies("remove").logoutSuccessUrl("/login.html").permitAll()
//                .and()
//                .httpBasic();


//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .permitAll()
//                .and().csrf().disable()//.anonymous().disable()
//                .logout()
//                .permitAll();
    }

}

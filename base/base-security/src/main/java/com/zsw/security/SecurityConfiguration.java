package com.zsw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author ZhangShaowei on 2017/9/20 15:38
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    /**
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("111111").roles("ADMIN").build());
        return manager;
    };

    /**
     *
     */
//    @Autowired
//    private UserDetailsService userDetailsService;

    /**
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        //inMemoryAuthentication 从内存中获取
//        auth.inMemoryAuthentication().withUser("admin").password("111111").roles("USER");

        //jdbcAuthentication从数据库中获取，但是默认是以security提供的表结构
        //usersByUsernameQuery 指定查询用户SQL
        //authoritiesByUsernameQuery 指定查询权限SQL
        //auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query)
        //      .authoritiesByUsernameQuery(query);

        //注入userDetailsService，需要实现userDetailsService接口
//        auth.userDetailsService(userDetailsService());
//    }

    /**
     * @param http HttpSecurity
     * @throws Exception e
     */
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/customer/get").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and().csrf().disable()//.anonymous().disable()
//                .logout()
//                .permitAll();
//    }


}

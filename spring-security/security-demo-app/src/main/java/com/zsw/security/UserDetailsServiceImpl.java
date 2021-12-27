package com.zsw.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/12/24 9:31
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService, InitializingBean {

    private UserDetailsService delegate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.delegate.loadUserByUsername(username);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("111111").roles("admin").build();
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("111111").roles("user").build();
        this.delegate = new InMemoryUserDetailsManager(admin, user);
    }
}

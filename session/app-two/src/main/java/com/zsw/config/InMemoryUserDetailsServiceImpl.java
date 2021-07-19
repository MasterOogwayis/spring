package com.zsw.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author ZhangShaowei on 2021/7/8 14:32
 */
@Component
public class InMemoryUserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(
                "admin",
                "{noop}111111",
                Collections.singletonList(new SimpleGrantedAuthority("SUPER_ADMIN"))
        );
    }
}

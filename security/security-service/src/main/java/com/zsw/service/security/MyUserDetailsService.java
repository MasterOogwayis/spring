package com.zsw.service.security;

import com.zsw.persistence.bean.User;
import com.zsw.service.userrole.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2017/9/21 14:13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MyUserDetailsService implements UserDetailsService {

    /**
     *
     */
    @Autowired
    private SecurityService securityService;

    /**
     * @param username username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = this.securityService.getByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return new org.springframework.security.core.userdetails.User(
                username, user.getPassword(), true,
                true, true, true,
                Collections.singleton(new SimpleGrantedAuthority("/customer/get")));
    }
}

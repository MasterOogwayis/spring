package com.zsw.service.security;

import com.zsw.persistence.bean.Role;
import com.zsw.persistence.bean.User;
import com.zsw.service.userrole.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2017/9/21 14:13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MyUserDetailsServiceImpl implements UserDetailsService {

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

        List<GrantedAuthority> auths = user.getRoles().stream()
                        .map(Role::getMark).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                username, user.getPassword(), true,
                true, true, true,
                auths);
    }
}

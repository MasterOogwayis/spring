package com.zsw.base.oauth2.user;

import com.zsw.base.oauth2.ClientUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * authorization server
 *
 * @author ZhangShaowei on 2020/7/2 11:02
 */
@Slf4j
@AllArgsConstructor
public abstract class AbstractClientUserDetailsService implements ClientUserDetailsService {

    /**
     * @param username username
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getName)
                .map(clientId -> loadUserByUsername(username, clientId))
                .orElseThrow(() -> new UsernameNotFoundException("username not found."));
    }
}

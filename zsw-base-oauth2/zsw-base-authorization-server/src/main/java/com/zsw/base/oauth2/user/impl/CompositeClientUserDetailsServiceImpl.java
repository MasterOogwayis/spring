package com.zsw.base.oauth2.user.impl;

import com.zsw.base.oauth2.ClientUserDetailsService;
import com.zsw.base.oauth2.user.AbstractClientUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


/**
 * @author ZhangShaowei on 2020/7/3 14:08
 */
@AllArgsConstructor
public class CompositeClientUserDetailsServiceImpl extends AbstractClientUserDetailsService {

    private final List<ClientUserDetailsService> services;

    @Override
    public UserDetails loadUserByUsername(String username, String clientId) {
        return services.stream()
                .filter(service -> service.supports(clientId, username))
                .findFirst()
                .map(service -> service.loadUserByUsername(username, clientId))
                .orElseThrow(() -> new UsernameNotFoundException("user not found."));
    }

    /**
     * @param clientId clientId
     * @param username username
     * @return support
     */
    @Override
    @Deprecated
    public boolean supports(String clientId, String username) {
        return false;
    }

}

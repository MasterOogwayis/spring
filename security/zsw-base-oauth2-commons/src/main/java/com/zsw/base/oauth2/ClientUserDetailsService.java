package com.zsw.base.oauth2;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 带 clientId 的 UserDetailsService，便于不同业务不同处理
 *
 * @author ZhangShaowei on 2020/7/2 16:12
 * @see UserDetailsService
 */
public interface ClientUserDetailsService extends UserDetailsService {


    /**
     * 带 clientId
     *
     * @param username username
     * @param clientId clientId
     * @return UserDetails
     * @throws UsernameNotFoundException e
     */
    UserDetails loadUserByUsername(String username, String clientId) throws UsernameNotFoundException;


    /**
     * 退出
     *
     * @param username username
     * @param clientId clientId
     */
    default void logout(String username, String clientId) {
        // do nothing
    }


    /**
     * 支持的客户端
     *
     * @param clientId clientId
     * @param username username
     * @return boolean
     */
    default boolean supports(String clientId, String username) {
        return Boolean.FALSE;
    }

}

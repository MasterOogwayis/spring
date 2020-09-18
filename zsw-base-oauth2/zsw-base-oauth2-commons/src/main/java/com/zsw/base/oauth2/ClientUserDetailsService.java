package com.zsw.base.oauth2;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author ZhangShaowei on 2020/7/2 16:12
 */
public interface ClientUserDetailsService extends UserDetailsService {

    /**
     *
     */
    String LOGIN_TIMESTAMP = "timestamp";


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
     * 缓存用户信息
     *
     * @param username username
     * @param clientId clientId
     * @param attr     Object
     * @param expire   超时时间  单位 秒
     */
    default void save(String username, String clientId, Object attr, int expire) {
        // do nothing
    }

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
     * 缓存 key
     *
     * @param username username
     * @param clientId clientId
     * @return redis key
     */
    default String cacheKey(String username, String clientId) {
        return "security:oauth2:userInfo:" + clientId + ":" + username;
    }


    /**
     * 支持的客户端
     *
     * @param clientId clientId
     * @param username username
     * @return boolean
     */
    boolean supports(String clientId, String username);

}

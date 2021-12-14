package com.zsw.base.oauth2.resource;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

/**
 * 用于保存一些额外信息，以便于 resource 能获取，通常是 缓存实现
 *
 * @author ZhangShaowei on 2020/7/2 16:12
 */
public interface ResourceUserDetailsService {

    /**
     *
     */
    String LOGIN_TIMESTAMP = "timestamp";
    String USER_INFO = "sdp:security:oauth2:userInfo";

    /**
     * 带 clientId
     *
     * @param username username
     * @param clientId clientId
     * @return UserDetails
     * @throws UsernameNotFoundException e
     */
    default ResourceUserDetails loadUserByUsername(String username, String clientId) {
        return null;
    }

    ;

    /**
     * 缓存用户信息
     *
     * @param username username
     * @param clientId clientId
     * @param attr     Object
     * @param expire   超时时间  单位 秒
     */
    default void save(String username, String clientId, Map<String, Object> attr, int expire) {
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
        return USER_INFO + ":" + clientId + ":" + username.toLowerCase();
    }

}

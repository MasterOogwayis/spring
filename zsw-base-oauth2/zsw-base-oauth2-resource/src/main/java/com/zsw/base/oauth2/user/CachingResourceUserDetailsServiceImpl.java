package com.zsw.base.oauth2.user;

import com.zsw.base.oauth2.ClientUserDetailsService;
import com.zsw.base.oauth2.support.ClientUserDetails;
import com.sun.xml.internal.ws.addressing.model.ActionNotSupportedException;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

/**
 * @author ZhangShaowei on 2020/3/2 14:47
 */
@AllArgsConstructor
public class CachingResourceUserDetailsServiceImpl implements ClientUserDetailsService {


    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username, String clientId) throws UsernameNotFoundException {
        //noinspection unchecked
        return new ClientUserDetails(
                clientId,
                username,
                () -> (Map<String, Object>) this.redisTemplate.opsForValue().get(this.cacheKey(username, clientId))
        );
    }

    @Override
    public void logout(String username, String clientId) {
        this.redisTemplate.delete(this.cacheKey(username, clientId));
    }

    @Override
    public boolean support(String clientId, String username) {
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new ActionNotSupportedException("Not supported!");
    }
}

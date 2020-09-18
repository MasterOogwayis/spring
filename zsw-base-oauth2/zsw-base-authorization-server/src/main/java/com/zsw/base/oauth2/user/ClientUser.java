package com.zsw.base.oauth2.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

/**
 * 认证服务器的 user 信息(username, password)，用于鉴权
 * 如果需要在 token 附加额外信息，请使用这个对象，并设置 attr
 *
 * @author ZhangShaowei on 2020/7/3 10:54
 */
@Getter
@Setter
public class ClientUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 7878312908057951083L;

    public ClientUser(String username, String password, boolean enabled,
                      boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    /**
     * 使用 Map 以消除类型影响
     * 缓存的用户附加信息 cacheKey -> security : oauth2 : userInfo : clientId : username
     */
    private Map<String, ?> attr;

}

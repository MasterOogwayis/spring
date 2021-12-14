package com.zsw.base.oauth2.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * resource 端 session 中的用户信息
 *
 * @author ZhangShaowei on 2020/7/2 16:06
 */
@Getter
@Setter
public class ResourceUserDetails implements UserDetails {

    private static final long serialVersionUID = -6754249033033902646L;


    public ResourceUserDetails(String clientId, String username, Supplier<Map<String, Object>> supplier) {
        this.clientId = clientId;
        this.username = username;
        this.supplier = supplier;
    }

    private final String clientId;

    private final String username;

    private Long loginTimestamp;
    /**
     * 使用懒加载的方式提供附加信息，若不使用则不进行 io
     */
    @JsonIgnore
    private final Supplier<Map<String, Object>> supplier;

    /**
     *
     */
    private Map<String, Object> attr;

    /**
     *
     */
    public Map<String, Object> getAttr() {
        return Optional.ofNullable(attr).orElseGet(() -> attr = supplier.get());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}

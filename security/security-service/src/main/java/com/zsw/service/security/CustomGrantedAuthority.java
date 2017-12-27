package com.zsw.service.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author ZhangShaowei on 2017/12/27 10:25
 */

public class CustomGrantedAuthority implements GrantedAuthority {


    private static final long serialVersionUID = 7293749252942780632L;

    @Override
    public String getAuthority() {
        return null;
    }
}

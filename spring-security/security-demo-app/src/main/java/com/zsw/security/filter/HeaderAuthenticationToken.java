package com.zsw.security.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author ZhangShaowei on 2022/1/6 17:23
 */
public class HeaderAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public HeaderAuthenticationToken(Object principal) {
        super(principal, principal);
    }
}

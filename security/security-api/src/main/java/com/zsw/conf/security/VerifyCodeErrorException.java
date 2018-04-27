package com.zsw.conf.security;

import org.springframework.security.core.AuthenticationException;

/**
 * VerfyCode Error
 *
 * @author ZhangShaowei on 2018/4/26 14:46
 **/
public class VerifyCodeErrorException extends AuthenticationException {
    public VerifyCodeErrorException(String msg, Throwable t) {
        super(msg, t);
    }

    public VerifyCodeErrorException(String msg) {
        super(msg);
    }
}

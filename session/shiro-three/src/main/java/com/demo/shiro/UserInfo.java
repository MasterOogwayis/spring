package com.demo.shiro;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @author ZhangShaowei on 2021/7/26 11:03
 */
@Getter
@Setter
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 4782144001697287740L;
    private String username;

    private String password;

    private Set<String> roles;


}

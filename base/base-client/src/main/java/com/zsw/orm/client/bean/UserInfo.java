package com.zsw.orm.client.bean;

import java.util.List;

/**
 * 这是角色信息
 *
 * @author ZhangShaowei on 2017/10/19 16:41
 */

public class UserInfo {

    /**
     * 登陆用户名
     */
    private String username;

    /**
     * 角色
     */
    private List<String> roles;


    /**  */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**  */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * @param roles
     */
    public void setRoles(final List<String> roles) {
        this.roles = roles;
    }
}

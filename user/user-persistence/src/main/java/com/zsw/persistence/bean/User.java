package com.zsw.persistence.bean;


import com.zsw.base.bean.commons.BaseBean;

import javax.persistence.*;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/9/12 13:35
 */
@Entity
@Table(name = "USER")
public class User extends BaseBean {

    private static final long serialVersionUID = -6630917103972003205L;


    /**
     *
     */
    @Column(name = "USER_NAME", nullable = false, columnDefinition = "")
    private String username;

    /**
     *
     */
    @Column(name = "PASSWORD", nullable = false)
    private String password;


    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRole> roles;

    /**  */
    public String getUsername() {
        return username;
    }

    /**  */
    public void setUsername(String username) {
        this.username = username;
    }

    /**  */
    public String getPassword() {
        return password;
    }

    /**  */
    public void setPassword(String password) {
        this.password = password;
    }

    /**  */
    public List<UserRole> getRoles() {
        return roles;
    }

    /**  */
    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}

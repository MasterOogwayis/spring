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
    @Column(name = "USER_NAME", nullable = false)
    private String username;

    /**
     *
     */
    @Column(name = "PASSWORD", nullable = false)
    private String password;


    /**
     *
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"),
            foreignKey = @ForeignKey(name = "user_role_ibfk_1"),
            inverseForeignKey = @ForeignKey(name = "user_role_ibfk_2"))
    private List<Role> roles;

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
    public List<Role> getRoles() {
        return roles;
    }

    /**  */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

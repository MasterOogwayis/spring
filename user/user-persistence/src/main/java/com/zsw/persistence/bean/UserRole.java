package com.zsw.persistence.bean;

import com.zsw.base.bean.commons.BaseBean;

import javax.persistence.*;

/**
 * @author ZhangShaowei on 2017/9/21 14:01
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRole extends BaseBean {

    private static final long serialVersionUID = 3390362341679321609L;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;


    /**  */
    public User getUser() {
        return user;
    }

    /**  */
    public void setUser(User user) {
        this.user = user;
    }

    /**  */
    public Role getRole() {
        return role;
    }

    /**  */
    public void setRole(Role role) {
        this.role = role;
    }
}

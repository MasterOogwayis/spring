package com.zsw.persistence.bean;

import com.zsw.base.bean.commons.BaseBean;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author ZhangShaowei on 2017/9/21 14:01
 */
//@Entity
//@Table(name = "ROLE_AUTH")
public class RoleAuth extends BaseBean {

    private static final long serialVersionUID = 3390362341679321609L;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "USER_ROLE_ID", nullable = false)
    private UserRole userRole;

    /**
     *
     */
    @Column(name = "URI_SOURCE", nullable = false)
    private String uriSource;

    /**
     *
     */
    @Column(name = "METHOD", nullable = false)
    private Integer method;


    /**  */
    public UserRole getUserRole() {
        return userRole;
    }

    /**  */
    public void setUser(UserRole userRole) {
        this.userRole = userRole;
    }

    /**  */
    public String getUriSource() {
        return uriSource;
    }

    /**  */
    public void setUriSource(String uriSource) {
        this.uriSource = uriSource;
    }

    /**  */
    public Integer getMethod() {
        return method;
    }

    /**  */
    public void setMethod(Integer method) {
        this.method = method;
    }
}

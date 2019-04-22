package com.zsw.persistence.user.bean;

import com.zsw.orm.bean.commons.BaseBean;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author ZhangShaowei on 2017/9/21 14:01
 */
@Getter
@Setter
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


}

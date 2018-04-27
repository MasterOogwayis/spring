package com.zsw.persistence.timedtask.bean;

import com.zsw.base.bean.commons.BaseBean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author ZhangShaowei on 2017/9/21 14:01
 */
@Getter
@Setter
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


}

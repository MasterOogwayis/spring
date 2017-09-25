package com.zsw.persistence.bean;

import com.zsw.base.bean.commons.BaseBean;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    /**
     *
     */
    @Column(name = "MARK", nullable = false)
    private String mark;


    /**  */
    public User getUser() {
        return user;
    }

    /**  */
    public void setUser(User user) {
        this.user = user;
    }

    /**  */
    public String getMark() {
        return mark;
    }

    /**  */
    public void setMark(String mark) {
        this.mark = mark;
    }
}

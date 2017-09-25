package com.zsw.persistence.bean;

import com.zsw.base.bean.commons.BaseBean;

import javax.persistence.*;

/**
 * @author ZhangShaowei on 2017/9/15 10:18
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseBean {
    private static final long serialVersionUID = 6241220484073091377L;

    /**
     *
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     *
     */
    @Column(name = "MOBILE", nullable = false)
    private String mobile;

    /**
     *
     */
    @Column(name = "AGE", nullable = false, columnDefinition = "0")
    private Integer age;

    /**
     *
     */
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    /**
     *
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;


    /**  */
    public String getName() {
        return name;
    }

    /**  */
    public void setName(String name) {
        this.name = name;
    }

    /**  */
    public Integer getAge() {
        return age;
    }

    /**  */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**  */
    public String getAddress() {
        return address;
    }

    /**  */
    public void setAddress(String address) {
        this.address = address;
    }

    /**  */
    public User getUser() {
        return user;
    }

    /**  */
    public void setUser(User user) {
        this.user = user;
    }

    /**  */
    public String getMobile() {
        return mobile;
    }

    /**  */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

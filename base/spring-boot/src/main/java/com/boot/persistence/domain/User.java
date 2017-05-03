package com.boot.persistence.domain;

import com.boot.persistence.domain.base.BaseBean;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ZhangShaowei on 2017/4/24 14:33
 */
@Entity
@Table(name = "USER")
public class User extends BaseBean {

    /**  */
    private String name;

    /**  */
    private Integer age;

    /**  */
    private String gender;

    /**  */
    private String address;

    /**  */
    private String password;

    /**  */
    private Date createDate;

    /**  */
    public Integer getAge() {
        return age;
    }

    /**  */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**  */
    public String getGender() {
        return gender;
    }

    /**  */
    public void setGender(String gender) {
        this.gender = gender;
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
    public Date getCreateDate() {
        return createDate;
    }

    /**  */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**  */
    public String getName() {
        return name;
    }

    /**  */
    public void setName(String name) {
        this.name = name;
    }

    /**  */
    public String getPassword() {
        return password;
    }

    /**  */
    public void setPassword(String password) {
        this.password = password;
    }
}

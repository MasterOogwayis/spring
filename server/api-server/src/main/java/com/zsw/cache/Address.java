package com.zsw.cache;

import com.zsw.base.bean.commons.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * demo
 *
 * @author ZhangShaowei on 2018/3/8 13:56
 **/
@Entity
@Table(name = "ADDRESS")
public class Address extends BaseBean {

    /**
     *
     */
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    /**  */
    public String getAddress() {
        return address;
    }

    /**  */
    public void setAddress(String address) {
        this.address = address;
    }
}

package com.zsw.persistence.bean;

import com.zsw.base.bean.commons.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author ZhangShaowei on 2017/10/12 11:15
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseBean {

    private static final long serialVersionUID = 8452112114019075811L;


    /**
     *
     */
    @Column(name = "NUMBER", nullable = false)
    private Integer number;


    /**  */
    public Integer getNumber() {
        return number;
    }

    /**  */
    public void setNumber(Integer number) {
        this.number = number;
    }
}

package com.zsw.persistence.user.bean;

import com.zsw.orm.bean.commons.BaseBean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author ZhangShaowei on 2017/10/12 11:15
 */
@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseBean {

    private static final long serialVersionUID = 8452112114019075811L;


    /**
     *
     */
    @Column(name = "NUMBER", nullable = false)
    private Integer number;

}

package com.zsw.cache;

import com.zsw.base.bean.commons.BaseBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * demo
 *
 * @author ZhangShaowei on 2018/3/8 13:56
 **/
@Data
@Entity
@Table(name = "ADDRESS")
public class Address extends BaseBean {

    private static final long serialVersionUID = 5895770478313468041L;
    /**
     *
     */
    @Column(name = "ADDRESS", nullable = false)
    private String address;

}

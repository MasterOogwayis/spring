package com.zsw.persistence.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Shaowei Zhang on 2019/4/19 22:43
 **/
@Data
public class Customer implements Serializable {
    private static final long serialVersionUID = 4751520371939361257L;

    private Long id;

    private String name;

    private Integer age;

    private Date createDate;


}

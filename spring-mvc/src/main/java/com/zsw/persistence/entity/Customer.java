package com.zsw.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Shaowei Zhang on 2019/4/19 22:43
 **/
@Data
@Entity
@Table(name = "t_customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 4751520371939361257L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private Date createDate;


}

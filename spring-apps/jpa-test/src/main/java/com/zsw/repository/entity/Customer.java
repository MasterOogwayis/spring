package com.zsw.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ZhangShaowei on 2019/12/26 10:54
 **/
@Getter
@Setter
@Entity
@Table(name = "t_customer")
public class Customer {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    @Column(name = "name")
    private String name;

}

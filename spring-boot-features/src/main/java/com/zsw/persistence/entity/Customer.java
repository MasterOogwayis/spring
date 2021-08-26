package com.zsw.persistence.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhangShaowei on 2021/8/26 10:26
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "t_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = -5168764260002876734L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 19)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "creator", nullable = false, updatable = false)
    private String creator;

    @Column(name = "createTime", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "updator")
    private String updator;

    @Column(name = "updateTime")
    private LocalDateTime updateTime;


}

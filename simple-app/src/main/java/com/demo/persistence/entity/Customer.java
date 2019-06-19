package com.demo.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhangShaowei on 2019/6/19 16:38
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Customer implements Serializable {
    private static final long serialVersionUID = 2137228635161247551L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 19)
    private Long id;

    @Column
    private String name;

    @Column
    private String mobile;

    @Column
    private Integer age;

    @Column
    private String address;

    @Column
    private Long userId;

    @Column
    private Date createTime;

}

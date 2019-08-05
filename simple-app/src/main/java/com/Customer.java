package com;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/6/20 15:27
 **/
@Getter
@Setter
@Entity
@Table(name = "t_customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer implements Serializable {
    private static final long serialVersionUID = 6233251060660221414L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

}

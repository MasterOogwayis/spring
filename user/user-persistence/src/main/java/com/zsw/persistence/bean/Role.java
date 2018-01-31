package com.zsw.persistence.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is role
 *
 * @author ZhangShaowei on 2018/1/30 11:20
 **/
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {


    private static final long serialVersionUID = 2067392708805527589L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    /**
     *
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     *
     */
    @Column(name = "MARK", nullable = false)
    private String mark;

    /**  */
    public Long getId() {
        return id;
    }

    /**  */
    public void setId(Long id) {
        this.id = id;
    }

    /**  */
    public String getName() {
        return name;
    }

    /**  */
    public void setName(String name) {
        this.name = name;
    }

    /**  */
    public String getMark() {
        return mark;
    }

    /**  */
    public void setMark(String mark) {
        this.mark = mark;
    }
}

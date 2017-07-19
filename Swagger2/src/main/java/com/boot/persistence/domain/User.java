package com.boot.persistence.domain;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2017/7/11 16:34
 */

public class User implements Serializable {

    private static final long serialVersionUID = -8419893712648389008L;

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer age;

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
    public Integer getAge() {
        return age;
    }

    /**  */
    public void setAge(Integer age) {
        this.age = age;
    }
}

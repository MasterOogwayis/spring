package com.boot.persistence.domain.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ZhangShaowei on 2017/4/24 14:35
 */
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseBean implements Serializable {


    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    /**  */
    public Long getId() {
        return id;
    }

    /**  */
    public void setId(Long id) {
        this.id = id;
    }
}

package com.zsw.base.bean.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ZhangShaowei on 2017/9/12 13:28
 */
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseBean implements Serializable {
    private static final long serialVersionUID = 4018353784257038197L;

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

    /**
     * @param id primary key
     */
    public void setId(final Long id) {
        this.id = id;
    }
}
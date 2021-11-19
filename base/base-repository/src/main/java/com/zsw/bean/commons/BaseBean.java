package com.zsw.bean.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhangShaowei on 2017/9/12 13:28
 */
@Getter
@Setter
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseBean implements Serializable {
    private static final long serialVersionUID = 4018353784257038197L;

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    /**
     *
     */
    @Column(name = "CREATOR", nullable = false, updatable = false)
    protected String creator;

    /**
     *
     */
    @Column(name = "CREATE_TIMESTAMP", nullable = false, updatable = false)
    protected Date createTimestamp;

    /**
     *
     */
    @Column(name = "UPDATOR")
    protected String updator;

    /**
     *
     */
    @Column(name = "UPDATE_TIMESTAMP")
    protected Date updateTimestamp;
}

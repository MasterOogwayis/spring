package com.persistence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is a demo.
 *
 * @author ZhangShaowei on 2018/3/23 13:17
 **/
@Entity
@Table(name = "TICKET")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 8999738356793348139L;
    /**
     *
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    private String id;

    /**
     *
     */
    @Column(name = "NUMBER", nullable = false, columnDefinition = "INT(11) DEFAULT 0")
    private Integer number;

    /**  */
    public String getId() {
        return id;
    }

    /**  */
    public void setId(String id) {
        this.id = id;
    }

    /**  */
    public Integer getNumber() {
        return number;
    }

    /**  */
    public void setNumber(Integer number) {
        this.number = number;
    }
}

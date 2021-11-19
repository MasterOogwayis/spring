package com.zsw.peprsistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * This is a demo.
 *
 * @author ZhangShaowei on 2018/3/23 13:17
 **/
@Getter
@Setter
//@Entity
//@Table(name = "TICKET")
public class Ticket implements Serializable {

    @Serial
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


}

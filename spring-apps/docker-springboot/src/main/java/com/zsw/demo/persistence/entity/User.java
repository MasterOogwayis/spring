package com.zsw.demo.persistence.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.createDate
     *
     * @mbg.generated
     */
    private Date createdate;

}
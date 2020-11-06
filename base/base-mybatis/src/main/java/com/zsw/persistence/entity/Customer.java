package com.zsw.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Shaowei Zhang
 */
@Data
@TableName("customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 5125128474470259844L;
    /**
     *
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     *
     */
    @TableField("name")
    private String name;

    /**
     *
     */
    @TableField("age")
    private Integer age;

    /**
     *
     */
    @TableField("createTime")
    private LocalDateTime createTime;
}
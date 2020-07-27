package com.zsw.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Shaowei Zhang
 */
@Data
@TableName("city")
public class City implements Serializable {
    private static final long serialVersionUID = 3581881138117397982L;
    /**
     *
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField("`Name`")
    private String name;

    /**
     *
     */
    @TableField("CountryCode")
    private String countryCode;

    /**
     *
     */
    @TableField("District")
    private String district;

    /**
     *
     */
    @TableField("Population")
    private Integer population;

}
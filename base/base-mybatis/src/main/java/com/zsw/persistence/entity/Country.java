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
@TableName("country")
public class Country implements Serializable {
    private static final long serialVersionUID = 5125128474470259844L;
    /**
     *
     */
    @TableId(value = "Code", type = IdType.INPUT)
    private String code;

    /**
     *
     */
    @TableField("Name")
    private String name;

    /**
     *
     */
    @TableField("Continent")
    private String continent;

    /**
     *
     */
    @TableField("Region")
    private String region;

    /**
     *
     */
    @TableField("SurfaceArea")
    private Float surfaceArea;

    /**
     *
     */
    @TableField("IndepYear")
    private Short indepYear;

    /**
     *
     */
    @TableField("Population")
    private Integer population;

    /**
     *
     */
    @TableField("LifeExpectancy")
    private Float lifeExpectancy;

    /**
     *
     */
    @TableField("GNP")
    private Float gnp;

    /**
     *
     */
    @TableField("GNPOld")
    private Float gnpOld;

    /**
     *
     */
    @TableField("LocalName")
    private String localName;

    /**
     *
     */
    @TableField("GovernmentForm")
    private String governmentForm;

    /**
     *
     */
    @TableField("HeadOfState")
    private String headOfState;

    /**
     *
     */
    @TableField("Capital")
    private Integer capital;

    /**
     *
     */
    @TableField("Code2")
    private String code2;
}
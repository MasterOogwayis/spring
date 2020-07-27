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
@TableName("countrylanguage")
public class CountryLanguage implements Serializable {
    private static final long serialVersionUID = 2241232078511478462L;

    /**
     *
     */
    @TableId(value = "CountryCode", type = IdType.INPUT)
    private String countryCode;

    /**
     *
     */
    @TableField("Language")
    private String language;

    /**
     *
     */
    @TableField("IsOfficial")
    private String isOfficial;

    /**
     *
     */
    @TableField("Percentage")
    private Float percentage;


}
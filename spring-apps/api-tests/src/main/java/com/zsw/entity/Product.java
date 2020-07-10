package com.zsw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhangShaowei
 */
@Data
@TableName("t_product")
public class Product implements Serializable {
    private static final long serialVersionUID = -6874159114545160553L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product.id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product.productName
     */
    @TableField("productName")
    private String productName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product.count
     */
    @TableField
    private Integer quantity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product.price
     */
    @TableField
    private Double price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product.address
     */
    @TableField
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product.time
     */
    @TableField
    private LocalDateTime createTime;

}
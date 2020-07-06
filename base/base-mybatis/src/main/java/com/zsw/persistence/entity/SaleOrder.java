package com.zsw.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhangShaowei on 2020/5/12 9:51
 */
@Data
@TableName("t_sale_order")
public class SaleOrder implements Serializable {
    private static final long serialVersionUID = -6503001070769700536L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField
    private String name;

    @TableField("orderNumber")
    private String orderNumber;

    @TableField
    private String description;

    @TableField
    private Integer price;

    @TableField("createDate")
    private Date createDate;

}

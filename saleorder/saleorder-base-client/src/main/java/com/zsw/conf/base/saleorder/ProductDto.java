package com.zsw.conf.base.saleorder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author ZhangShaowei on 2017/11/6 16:12
 */
@Getter
@Setter
@ToString
public class ProductDto {

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Date createTimestamp;


    /**
     *
     */
    private Integer number;


}

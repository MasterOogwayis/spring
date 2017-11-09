package com.zsw.client.base.saleorder;

import java.util.Date;

/**
 * @author ZhangShaowei on 2017/11/6 16:12
 */

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


    /**  */
    public Long getId() {
        return id;
    }

    /**  */
    public void setId(Long id) {
        this.id = id;
    }

    /**  */
    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    /**  */
    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
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

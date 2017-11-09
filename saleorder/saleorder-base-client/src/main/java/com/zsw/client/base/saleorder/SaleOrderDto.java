package com.zsw.client.base.saleorder;

import java.util.Date;

/**
 * @author ZhangShaowei on 2017/11/6 16:10
 */

public class SaleOrderDto {

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
    private Integer quantity;

    /**
     *
     */
    private ProductDto product;


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
    public Integer getQuantity() {
        return quantity;
    }

    /**  */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**  */
    public ProductDto getProduct() {
        return product;
    }

    /**  */
    public void setProduct(ProductDto product) {
        this.product = product;
    }
}

package com.zsw.persistence.bean;

import com.zsw.base.bean.commons.BaseBean;

import javax.persistence.*;

/**
 * @author ZhangShaowei on 2017/10/12 15:12
 */
@Entity
@Table(name = "SALE_ORDER")
public class SaleOrder extends BaseBean {
    private static final long serialVersionUID = 647604975520366322L;


    /**
     *
     */
    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;


    /**  */
    public Integer getQuantity() {
        return quantity;
    }

    /**  */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**  */
    public Product getProduct() {
        return product;
    }

    /**  */
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "SaleOrder{" +
                "quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}

package com.zsw.persistence.user.bean;

import com.zsw.base.bean.commons.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author ZhangShaowei on 2017/10/12 15:12
 */
@Getter
@Setter
@ToString
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
}

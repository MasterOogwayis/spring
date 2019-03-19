package com;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 物流信息
 *
 * @author ZhangShaowei on 2018/11/20 17:33
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceLogisticsDto implements Serializable {

    /**
     * 承运公司
     *
     * length = 200
     * required = false
     */
    private String CYGS;

    /**
     * 送货时间 yyyy-MM-dd HH:mm:ss
     *
     * length = 19
     * required = false
     */
    private String SHSJ;

    /**
     * 物流订单
     *
     * length = 20
     * required = false
     */
    private String WLDH;

    /**
     * 送货地址
     *
     * length = 200
     * required = false
     */
    private String SHDZ;
}

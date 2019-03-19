package com;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付信息
 *
 * @author ZhangShaowei on 2018/11/20 17:32
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoicePaymentDto implements Serializable {

    /**
     * 支付方式
     *
     * length = 20
     * required = false
     */
    private String ZFFS;

    /**
     * 支付流水号
     *
     * length = 20
     * required = false
     */
    private String ZFLSH;

    /**
     * 支付平台
     *
     * length = 20
     * required = false
     */
    private String ZFPT;
}

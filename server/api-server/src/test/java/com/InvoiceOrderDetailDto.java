package com;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单明细
 *
 * @author ZhangShaowei on 2018/11/20 17:31
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceOrderDetailDto implements Serializable {

    /**
     * TODO
     * 名称
     *
     * length = 200
     * required = false
     */
    private String DDMC;

    /**
     * TODO
     * 单位
     *
     * length = 200
     * required = false
     */
    private String DW;

    /**
     * TODO
     * 规格型号
     *
     * length = 200
     * required = false
     */
    private String GGXH;

    /**
     * TODO
     * 数量
     * 小数点后8 位, 小数点后都是0 时，PDF 上只显示整数
     *
     * length = 24
     * required = false
     */
    private String SL;

    /**
     * TODO
     * 单价
     * 小数点后8 位小数点后都是0 时，PDF 上只显示2 位小数；否则只显示至最后一位不为0 的数字
     *
     * length = 24
     * required = false
     */
    private String DJ;

    /**
     * TODO
     * 金额
     * 小数点后2 位，以元为单位精确到分
     *
     * length = 16
     * required = false
     */
    private String JE;

    /**
     * 备用字段1
     */
    private String BYZD1;

    /**
     * 备用字段2
     */
    private String BYZD2;

    /**
     * 备用字段3
     */
    private String BYZD3;

    /**
     * 备用字段4
     */
    private String BYZD4;

    /**
     * 备用字段5
     */
    private String BYZD5;

}

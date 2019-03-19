package com;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * 开票的消息体
 *
 * @author ZhangShaowei on 2018/11/20 13:56
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateInvoiceDto implements Serializable {

    public static final String ENCRYPT_NO = "0";
    public static final String ENCRYPT_YES = "1";

    public static final String ZIP_NO = "0";
    public static final String ZIP_YES = "1";
    private static final long serialVersionUID = -536291581510253471L;

    /**
     * 发票信息
     */
    @NonNull
    @SerializedName("FPKJXX_FPTXX")
    private InvoiceInfoDto invoiceInfoDto;

    /**
     * 项目信息
     */
    @NonNull
    @SerializedName("FPKJXX_XMXXS")
    private List<InvoiceProjectDto> invoiceProjectDtoList;

    /**
     * 订单信息
     */
    @NonNull
    @SerializedName("FPKJXX_DDXX")
    private InvoiceOrderDto invoiceOrderDto;

    /**
     * 订单明细信息 ， 多条
     */
    @SerializedName("FPKJXX_DDMXXXS")
    private List<InvoiceOrderDetailDto> orderDetailDtoList;

    /**
     * 支付信息
     */
    @Deprecated
    @SerializedName("FPKJXX_ZFXX")
    private InvoicePaymentDto paymentDto;

    /**
     * 物流信息
     */
    @Deprecated
    @SerializedName("FPKJXX_WLXX")
    private InvoiceLogisticsDto logisticsDto;


}

package com;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 订单信息
 *
 * @author ZhangShaowei on 2018/11/20 17:30
 **/
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceOrderDto implements Serializable {

    /**
     * 订单号
     * <p>
     * length = 20
     * required = true
     */
    @NotBlank(message = "订单号不能为空")
    private String DDH;

    /**
     * 退货单号 - 在开具红字发票退货、折让的时候必须填写
     * <p>
     * length = 20
     * required = flase
     */
    private String THDH;

    /**
     * 订单时间 yyyy-MM-dd HH:mm:ss
     * <p>
     * length = 19
     * required = false
     */
    private String DDDATE;


}

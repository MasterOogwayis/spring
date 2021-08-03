package com.zsw.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2021/8/3 10:15
 */
@Data
public class DriverProfit implements Serializable {
    private static final long serialVersionUID = 1134601785082024569L;

    private String name;
    private String phone;

    private Integer dailyProfit;

    private Integer netProfit;

    private Integer sdProfit;

    private Integer profitSharingRatio;

    private Double amount;

}

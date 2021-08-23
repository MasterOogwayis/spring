package com.zsw.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2021/8/20 16:09
 */
@Getter
@Setter
public class DriverCostDailyCell implements Serializable {

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("联系电话")
    private String phone;

    @ExcelProperty("燃料类型")
    private String fuelType;

    @ExcelProperty("座位数")
    private Integer seatNum;

    @ExcelProperty("是否换车")
    private String changeVehicle;


}

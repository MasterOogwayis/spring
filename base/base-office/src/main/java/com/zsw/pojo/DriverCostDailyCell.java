package com.zsw.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhangShaowei on 2021/8/20 16:09
 */
@Getter
@Setter
public class DriverCostDailyCell implements Serializable {

    private static final long serialVersionUID = -3888237046282784837L;

    @ExcelProperty({"驾驶员", "姓名"})
    private String name;

    @ExcelProperty({"驾驶员", "联系电话"})
    private String phone;

    @ExcelProperty({"车辆", "燃料类型"})
    private String fuelType;

    @ExcelProperty({"车辆", "座位数"})
    private Integer seatNum;

    @ExcelProperty({"车辆", "是否换车"})
    private String changeVehicle;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty({"日期", "日期"})
    private LocalDateTime date;


}

package com.zsw.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.apache.poi.ss.usermodel.Font.COLOR_RED;

/**
 * @author ZhangShaowei on 2021/8/20 16:09
 */
@Getter
@Setter
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)//表头样式
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)//内容样式
public class DriverCostDailyCell implements Serializable {

    private static final long serialVersionUID = -3888237046282784837L;

    @ExcelProperty({"驾驶员", "姓名"})
    private String name;

    @ColumnWidth(20)
    @ExcelProperty({"驾驶员", "联系电话"})
    private String phone;

    @ColumnWidth(20)
    @ExcelProperty({"车辆", "燃料类型"})
    private String fuelType;

    @ExcelProperty({"车辆", "座位数"})
    private Integer seatNum;

    @ColumnWidth(20)
    @ExcelProperty({"车辆", "车牌号码"})
    private String vehicleNo;

    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty({"日期", "日期"})
    private LocalDateTime date;

    @ContentFontStyle(color = COLOR_RED)
    @NumberFormat("#.##元")
    @ExcelProperty({"金额", "金额"})
    private Double amount;


}

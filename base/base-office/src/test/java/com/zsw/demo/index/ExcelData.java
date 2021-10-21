package com.zsw.demo.index;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2021/9/30 15:03
 */
@Getter
@Setter
@ToString
public class ExcelData implements Serializable {

    private static final long serialVersionUID = -298599189393907477L;
    @ExcelProperty(index = 0)
    private String obj1;

    @ExcelProperty(index = 1)
    private String obj2;

    @ExcelProperty(index = 2)
    private String obj3;

    @ExcelProperty(index = 3)
    private String obj4;

//    @ExcelProperty(index = 4)
//    private String obj5;

}

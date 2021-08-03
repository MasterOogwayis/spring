package com.zsw.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhangShaowei on 2021/8/3 10:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexOrNameData implements Serializable {

    private static final long serialVersionUID = -1329111423993318915L;
    /**
     * 强制读取第三个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
     */
    @ExcelProperty("数字标题")
//    @ExcelProperty(index = 2)
    private Double doubleData;
    /**
     * 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
     */
    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期标题")
    private LocalDateTime date;

}

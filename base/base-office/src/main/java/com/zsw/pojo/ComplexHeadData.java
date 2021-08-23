package com.zsw.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2021/8/20 15:57
 */
@Getter
@Setter
public class ComplexHeadData implements Serializable {

    @ExcelProperty({"顶格", "顶格", "两格"})
    private String string0;
    @ExcelProperty({"顶格", "顶格", "两格"})
    private String string1;
    @ExcelProperty({"顶格", "四联", "四联"})
    private String string2;
    @ExcelProperty({"顶格", "四联", "四联"})
    private String string3;
    @ExcelProperty({"顶格"})
    private String string4;

}

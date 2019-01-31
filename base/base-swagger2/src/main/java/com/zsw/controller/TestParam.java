package com.zsw.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/1/30 17:29
 **/
@Data
@ApiModel(value = "测试参数", description = "测试实体类参数")
public class TestParam implements Serializable {
    private static final long serialVersionUID = -8532644894217945961L;

    /**
     *
     */
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     *
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     *
     */
    @ApiModelProperty(value = "年龄", required = true)
    private Integer age;


}

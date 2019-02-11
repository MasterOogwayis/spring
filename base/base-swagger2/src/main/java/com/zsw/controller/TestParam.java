package com.zsw.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ApiModel value最好别使用中文，如果 亦或请用 ApiParam代理说明
 *
 * @author ZhangShaowei on 2019/1/30 17:29
 **/
@Data
@ApiModel(description = "测试实体类参数")
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

package com.zsw.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @ApiParam value 字段最好不要带特殊符号比如 斜杠 '/',会引起各种各样奇葩问题，描述写在description
 *
 * @author ZhangShaowei on 2019/1/30 17:31
 **/
@Data
@Builder
@ApiModel(description = "测试实体类返回值")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestResponse implements Serializable {
    private static final long serialVersionUID = -1116425045640372216L;

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

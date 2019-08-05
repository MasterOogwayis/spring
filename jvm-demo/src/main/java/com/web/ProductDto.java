package com.web;

import lombok.Getter;
import lombok.Setter;


/**
 * @author ZhangShaowei on 2019/4/12 15:46
 **/
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto extends BaseParam<String> {

    private static final long serialVersionUID = 6770541823646368625L;
    private DetailDto detailDto;

    private String number;

    private Long id;


}

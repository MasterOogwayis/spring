package com.web;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * id 为主键
 *
 * @author ZhangShaowei on 2019/1/30 15:50
 **/
@Data
@Builder
public class IdParam<T extends Serializable> extends BaseParam {

    private static final long serialVersionUID = 3181373688402729306L;
    /**
     * id
     */
    private T id;

}

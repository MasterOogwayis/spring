package com.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/2/14 16:44
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseParam<T> implements Serializable {
    private static final long serialVersionUID = -4363217718473775336L;


    /**
     * 操作员
     */
    protected T operator;

}

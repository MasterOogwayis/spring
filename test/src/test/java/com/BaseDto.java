package com;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2017/12/12 15:40
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDto implements Serializable {
    private static final long serialVersionUID = 4559351035638284080L;
}

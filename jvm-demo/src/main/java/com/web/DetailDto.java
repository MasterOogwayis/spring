package com.web;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/4/12 15:47
 **/
@Getter
@Setter
public class DetailDto implements Serializable {

    private static final long serialVersionUID = 1935027703454115834L;
    private Long id;

    private String name;

}

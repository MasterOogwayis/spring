package com.zsw.repository;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/12/26 16:14
 **/
@Getter
@Setter
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = -4700530475536893507L;
    private Long id;

    private String name;

}

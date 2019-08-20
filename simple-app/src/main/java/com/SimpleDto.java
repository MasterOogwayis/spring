package com;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/8/16 11:36
 **/
@Setter
@Getter
public class SimpleDto implements Serializable {

    private Long id;

    private String name;

    private String address;



}

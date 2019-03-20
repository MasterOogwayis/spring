package com.zsw.pattern.prototype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/3/20 9:16
 **/
@Getter
@Setter
@AllArgsConstructor
public class Drinks implements Serializable {
    private static final long serialVersionUID = 3883759521123043882L;

    private String name;


}

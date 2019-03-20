package com.zsw.pattern.prototype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 零食
 *
 * @author ZhangShaowei on 2019/3/20 9:17
 **/
@Getter
@Setter
@AllArgsConstructor
public class Snacks implements Serializable {

    private static final long serialVersionUID = -7766451816400344171L;
    private String name;

}

package com.zsw.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * @author Shaowei Zhang on 2022/3/9 16:22
 */
@Getter
@Setter
@FieldNameConstants
public class MainOrder {

    private String name;

    private Double price;

    private String address;

}

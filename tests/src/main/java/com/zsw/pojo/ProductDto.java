package com.zsw.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ZhangShaowei on 2022/1/24 16:31
 */
@Getter
@Setter
public class ProductDto implements Serializable{
    @Serial
    private static final long serialVersionUID = 4204020879615923686L;


    private Long id;

}

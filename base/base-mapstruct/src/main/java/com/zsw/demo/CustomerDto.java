package com.zsw.demo;

import lombok.*;

import java.util.List;

/**
 * @author ZhangShaowei on 2019/8/15 14:29
 **/
@Data
@Builder(builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;

    private String name;

    private List<ProductDTO> products;

}

package com.zsw.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author ZhangShaowei on 2019/8/15 14:29
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;

    private String name;

    private List<ProductDTO> products;

}

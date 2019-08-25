package com.zsw.rabbit.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author Administrator on 2019/8/25 19:19
 **/
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 8700029471554563403L;

    private Long id;

    private String name;

    private Double price;


}

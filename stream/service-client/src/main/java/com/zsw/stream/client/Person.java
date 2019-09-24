package com.zsw.stream.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/9/24 12:20
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = 8734331521753437645L;


    private String name;

    private Integer age;

    private String address;

}

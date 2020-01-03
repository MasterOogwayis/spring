package com.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2020/1/3 16:09
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = -5556067255471092904L;
    private Long id;

    private String name;

    private Integer age;

    private String address;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String address6;
    private String address7;
    private String address8;
    private String address9;
    private String address10;
    private String address11;
    private String address12;
    private String address13;
    private String address14;

}

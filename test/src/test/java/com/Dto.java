package com;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author ZhangShaowei on 2019/12/27 11:23
 **/
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dto {

    private Double amount;

    private Double price;

    private Double age;


    private Date date1;
    private Date date2;



}

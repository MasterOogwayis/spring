package com.demo;

import lombok.SneakyThrows;

/**
 * @author ZhangShaowei on 2019/3/4 11:30
 **/
public class TestClone {



    @SneakyThrows
    public static void main(String[] args) {

        CloneObject cloneObject = new CloneObject();
        Dto dto = new Dto();
        dto.setName("zsw");
        cloneObject.setDto(dto);

        CloneObject clone = (CloneObject) cloneObject.clone();

        System.out.println(clone);
        System.out.println(clone.getDto().equals(dto));

    }


}

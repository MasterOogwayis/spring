package com;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2019/10/9 10:03
 **/
public class StaticTests {

    public static void main(String[] args) {
        List<Dto> list = new ArrayList<>();
        list.add(new Dto(1L, 2));
        list.add(new Dto(1L, 1));
        list.add(new Dto(1L, 4));
        list.add(new Dto(1L, 3));
        list.add(new Dto(2L, 2));
        list.add(new Dto(2L, 2));
        list.add(new Dto(2L, 2));
        list.add(new Dto(2L, 2));
        list.add(new Dto(3L, 2));
        list.add(new Dto(3L, 2));
        list.add(new Dto(3L, 2));
        list.add(new Dto(3L, 2));



    }



    @Data
    @AllArgsConstructor
    static class Dto {
        private Long id;
        private Integer index;
    }

}

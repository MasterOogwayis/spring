package com.spring.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

/**
 * @author ZhangShaowei on 2021/12/21 10:53
 */
public class SecurityStaticTests {


    public static void main(String[] args) {
        Ob killer = Ob.builder()
                .id(1L)
                .name("Killer")
                .build();

        System.err.println(killer);

        System.out.println(Ob.Fields.name);


    }


    @SuperBuilder
    @FieldNameConstants
    @NoArgsConstructor
    @AllArgsConstructor
    static class Ob extends Parent {

        private String name;

    }

    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Parent {
        private Long id;
    }

}

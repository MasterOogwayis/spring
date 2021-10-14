package com.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author ZhangShaowei on 2021/10/14 17:48
 */
public class StreamTests {

    public static void main(String[] args) {
        Arrays.asList(
                new A("a",  List.of("1", "2", "3")),
                new A("a", List.of("4","5","6")),
                new A("b", List.of("7,","8,","9")),
                new A("b", List.of("10,","11,","12"))
        ).stream()
                .collect(Collectors.groupingBy(A::getName, Collectors.mapping(A::getArray, Collectors.mapping())));

    }



    @Data
    @AllArgsConstructor
    static class  A {
        private String name;
        private List<String> array;
    }
}

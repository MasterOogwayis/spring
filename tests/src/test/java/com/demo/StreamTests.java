package com.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * @author ZhangShaowei on 2021/10/14 17:48
 */
public class StreamTests {

    public static void main(String[] args) {
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(0, Integer::sum);
        System.out.println(reduce);


    }

    private static void test1() {
        List<A> list = Arrays.asList(
                new A("a", List.of("1", "2", "3")),
                new A("a", List.of("4", "5", "6")),
                new A("b", List.of("7", "8", "9")),
                new A("b", List.of("10", "11", "12"))
        );
        Map<String, List<String>> collect = list.stream()
                .collect(groupingBy(A::getName, mapping(A::getArray, flatMapping(List::stream, toList()))));
        System.out.println(collect);
    }


    @Data
    @AllArgsConstructor
    static class A {
        private String name;
        private List<String> array;
    }
}

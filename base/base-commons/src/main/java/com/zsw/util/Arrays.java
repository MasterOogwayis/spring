package com.zsw.util;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Shaowei Zhang on 2022/2/28 13:50
 */
public class Arrays {


    public static void main(String[] args) {
        List<Integer> integers = java.util.Arrays.asList(1, 2, 3, 4);

        integers.add(1);

        System.out.println(integers);

    }


}

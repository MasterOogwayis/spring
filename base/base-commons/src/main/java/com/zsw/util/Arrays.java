package com.zsw.util;

import lombok.NoArgsConstructor;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Shaowei Zhang on 2022/2/28 13:50
 */
@NoArgsConstructor
public class Arrays extends java.util.Arrays {


    public static <T> Stream<T> stream(T ... arr) {
        return stream(arr, 0, arr.length);
    }

    public static void main(String[] args) {
        Stream<Integer> stream = stream(1, 2, 3, 4, 5);
        int[] ints = {1, 2, 3, 4, 5};
    }



}

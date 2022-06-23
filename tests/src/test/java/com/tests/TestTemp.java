package com.tests;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Shaowei Zhang on 2022/3/14 16:09
 */
@Slf4j
public class TestTemp {

    public static void main(String[] args) {
        long timer = System.currentTimeMillis();
        List<Long> collect = Arrays.asList(1, 2, 3, 4)
                .parallelStream()
                .map(TestTemp::t)
                .collect(Collectors.toList());

        System.out.println(collect);

        log.info("cost {}ms", (System.currentTimeMillis() - timer));

    }

    private static long t(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return i;
    }

}

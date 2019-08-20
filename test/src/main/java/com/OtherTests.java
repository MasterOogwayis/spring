package com;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhangShaowei on 2019/8/14 13:14
 **/
@Slf4j
public class OtherTests {

    private static final TransmittableThreadLocal<String> CACHE = new TransmittableThreadLocal<>();
//    private static final ThreadLocal<String> CACHE = new ThreadLocal<>();

    public static void main(String[] args) {
        int length = 1000000;
        List<String> list = new ArrayList<>(length);

        CACHE.set("1");

        for (int i = 0; i < length; i++) {
            list.add(i + "");
        }
        AtomicInteger ai = new AtomicInteger(0);
        list.parallelStream().forEach(j -> {
            if (Objects.isNull(CACHE.get())) {
                ai.getAndIncrement();
            }
        });

        System.err.println(ai.get());



    }

}

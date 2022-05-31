package com.tests;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2022/4/19 16:45
 */
@Slf4j
public class CachingTests {


    @SneakyThrows
    public static void main(String[] args) {
        Cache<String, D> cache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .initialCapacity(100)
                .maximumSize(1000)
                .build();

        cache.put("key", new D(1L, "name", 18));

        for (int i = 0; i < 100; i++) {
            System.out.println(cache.getIfPresent("key"));
            TimeUnit.SECONDS.sleep(1);
        }

    }


    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class D {

        private Long id;

        private String name;

        private Integer age;

    }

}

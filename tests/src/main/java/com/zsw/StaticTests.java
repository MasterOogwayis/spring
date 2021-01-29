package com.zsw;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2020/12/28 16:03
 */
@Slf4j
public class StaticTests {

    @SneakyThrows
    public static void main(String[] args) {


        System.out.println("Hello World!");

    }


    public static void checkResult() {
        //        Executor executor = mock(Executor.class);
//        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> 0);
//        completableFuture.thenRunAsync(mock(Runnable.class), executor)
//                .whenComplete((s, e) -> {
//                    verify(executor, times(1)).execute(any(Runnable.class));
//                });

        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (Map.Entry<String, Charset> charsetEntry : map.entrySet()) {
            try {
                List<String> strings = Files.readAllLines(Paths.get("G:\\maotai\\jd_seckill.log"), charsetEntry.getValue());
                Map<String, List<String>> resultMap = strings
                        .stream()
                        .filter(str -> str.contains("orderId"))
                        .collect(Collectors.groupingBy(str -> {
                            if (str.contains("90016")) {
                                return "90016";
                            } else if (str.contains("90008")) {
                                return "90008";
                            } else {
                                return "unknown";
                            }
                        }));

                resultMap.forEach((key, list) -> {
                    log.info("resultCode={}, numbers={}", key, list.size());
                });
                log.info("--------------------------------------------------------- charset = {}", charsetEntry.getKey());
            } catch (Exception e) {
                log.error("编码错误：charset = {}", charsetEntry.getKey());
            }
        }
    }

}

package com.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2020/12/28 16:03
 */
@Slf4j
public class StaticTests {

    private static final ThreadLocal<String> POOL = new ThreadLocal<>();


    @SneakyThrows
    public static void main(String[] args) {

        IntStream.range(0, 10)
                .boxed()
                .sorted((a, b) -> -1)
                .forEach(System.out::println);

//        test(Collections.singletonList("Hello World"));


    }


    public static void test(Collection<String> collection) {
        Optional.ofNullable(collection).stream().flatMap(Collection::stream).forEach(System.out::println);
    }


    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int l = nums.length - 1;
        return nums[l] * nums[l - 1] * nums[l - 2];
    }


    @SneakyThrows
    public static void testDelayQueue() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        DelayQueue<DelayedUser> queue = new DelayQueue<>();
        executor.execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    DelayedUser delayedUser = new DelayedUser(System.currentTimeMillis() + "", 1000);
                    log.info("put delayedUser {}", delayedUser);
                    queue.put(delayedUser);
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    DelayedUser element = queue.take();
                    log.info("take {}", element);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
        });

        executor.awaitTermination(100, TimeUnit.SECONDS);
        executor.shutdown();
    }


    public static boolean t(Type type) {
        return String.class.equals(type);
    }

    public static class DelayedUser implements Delayed {

        private final String name;
        private final long time;

        public DelayedUser(String name, long delayTime) {
            this.name = name;
            this.time = System.currentTimeMillis() + delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.time - ((DelayedUser) o).time);
        }
    }

    @Getter
    @Setter
    static class Customer {

        @PostConstruct
        public void init() {
            log.info("init ...");
        }

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

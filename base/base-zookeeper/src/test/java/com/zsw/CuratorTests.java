package com.zsw;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author Shaowei Zhang on 2020/9/7 20:16
 */
@Slf4j
public class CuratorTests {

    private CuratorFramework client;

    private ThreadPoolExecutor executor;

    @Before
    public void before() {
        this.client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1")
                .sessionTimeoutMs(1000 * 1000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .connectionTimeoutMs(1000 * 1000)
                .build();
        this.client.start();

        this.executor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() * 2 + 1,
                200,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }


    @Test
    @SneakyThrows
    public void test() {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        InterProcessMutex lock = new InterProcessMutex(client, "/locks");
        for (int i = 0; i < 10; i++) {
            this.executor.execute(() -> {
                log.debug("{} -> 尝试抢占锁", Thread.currentThread().getName());
                try {
                    lock.acquire();
                    log.debug("{} -> 抢占锁成功", Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    TimeUnit.SECONDS.sleep(5);
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();

    }

    @After
    public void after() {
        this.client.close();
        this.executor.shutdown();
    }

}

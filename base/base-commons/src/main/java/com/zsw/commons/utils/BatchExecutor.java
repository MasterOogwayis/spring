package com.zsw.commons.utils;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/**
 * 批量执行工具类
 *
 * @author ZhangShaowei on 2020/5/28 15:03
 */
@AllArgsConstructor
public class BatchExecutor<T extends Serializable> {

    /**
     *
     */
    private final Collection<T> targets;

    /**
     *
     */
    private final Consumer<T> consumer;

    /**
     *
     */
    private final Executor executor;

    /**
     * 执行批量任务，所有任务执行完以后再返回
     */
    public void execute() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(this.targets.size());
        this.targets.forEach(target -> {
            BatchExecutor.this.executor.execute(() -> {
                try {
                    BatchExecutor.this.consumer.accept(target);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        });
        countDownLatch.await();
    }

}

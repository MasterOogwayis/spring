package com.zsw.commons.utils;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 批量执行工具类
 *
 * @author ZhangShaowei on 2020/5/28 15:03
 */
@AllArgsConstructor
public class BatchResultsExecutor<T extends Serializable, R extends Serializable> {

    private Collection<T> targets;
    private Function<T, R> function;
    private ExecutorService executorService;

    /**
     * 执行批量任务，所有任务执行完以后再返回
     */
    public List<R> execute() {
        return this.targets.stream()
                .map(ResultCall::new)
                .map(this.executorService::submit)
                .map(this::safeCall)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }

    public List<R> awaitExecute() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(this.targets.size());
        List<R> results = new ArrayList<>(this.targets.size());
        this.targets.forEach(target -> {
            BatchResultsExecutor.this.executorService.execute(() -> {
                try {
                    R apply = BatchResultsExecutor.this.function.apply(target);
                    results.add(apply);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        });
        countDownLatch.await();
        return results;

    }

    /**
     * @param future Future
     * @return R
     */
    private R safeCall(Future<R> future) {
        try {
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @AllArgsConstructor
    class ResultCall implements Callable<R> {

        private T target;

        @Override
        public R call() {
            return BatchResultsExecutor.this.function.apply(target);
        }
    }

}

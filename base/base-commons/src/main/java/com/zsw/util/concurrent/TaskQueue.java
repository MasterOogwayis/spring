package com.zsw.util.concurrent;

import lombok.Setter;

import java.io.Serial;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2022/2/24 9:38
 */
@Setter
public class TaskQueue<R extends Runnable> extends LinkedBlockingQueue<R> {
    @Serial
    private static final long serialVersionUID = -8053590970073047662L;

    private EagerThreadPoolExecutor executor;

    public TaskQueue(int capacity) {
        super(capacity);
    }

    public boolean retryOffer(R r, long timeout, TimeUnit unit) throws InterruptedException {
        if (executor.isShutdown()) {
            throw new RejectedExecutionException("Executor is shutdown!");
        }
        return super.offer(r, timeout, unit);
    }

    @Override
    public boolean offer(R r) {
        if (Objects.isNull(this.executor)) {
            throw new RejectedExecutionException("The TaskQueue does not have executor.");
        }
        int currentPoolSize = this.executor.getPoolSize();
        // 有空闲的 worker, 直接放入队列让 worker 处理
        if (this.executor.getSubmittedTaskCount() < currentPoolSize) {
            return super.offer(r);
        }

        // 当前线程数没有达到设置的上限，拒绝入队让 executor 继续增加 worker
        if (currentPoolSize < this.executor.getMaximumPoolSize()) {
            return false;
        }

        // currentPoolSize >= max
        return super.offer(r);
    }
}

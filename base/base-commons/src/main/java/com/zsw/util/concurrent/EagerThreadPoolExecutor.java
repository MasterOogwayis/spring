package com.zsw.util.concurrent;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhangShaowei on 2022/2/22 16:40
 */
public class EagerThreadPoolExecutor extends ThreadPoolExecutor {

    private final AtomicInteger submittedTaskCount = new AtomicInteger(0);

    public EagerThreadPoolExecutor(
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            TaskQueue<Runnable> workQueue,
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler
    ) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


    public int getSubmittedTaskCount() {
        return this.submittedTaskCount.get();
    }


    @Override
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }
        this.submittedTaskCount.incrementAndGet();
        try {
            super.execute(command);
        } catch (RejectedExecutionException re) {
            /*
              前面根据当前线程数小于最大线程数直接调用 addWorker
              但是 addWorker 有可能并发情况下失败，所以这里需要尝试重新入队
             */
            final TaskQueue<Runnable> queue = (TaskQueue<Runnable>) super.getQueue();
            try {
                if (!queue.retryOffer(command, 0, TimeUnit.MILLISECONDS)) {
                    this.submittedTaskCount.decrementAndGet();
                    throw new RejectedExecutionException("Queue capacity is full.", re);
                }
            } catch (InterruptedException e) {
                this.submittedTaskCount.decrementAndGet();
                throw new RejectedExecutionException(e);
            }

        } catch (Throwable e) {
            this.submittedTaskCount.decrementAndGet();
            throw e;
        }
    }
}

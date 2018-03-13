package com.zsw.service;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * This is a demo.
 *
 * @author ZhangShaowei on 2018/3/7 9:26
 **/
public class MQExecutor extends TransactionSynchronizationAdapter implements Executor {
    
    /**
     * logger
     */
    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(this.getClass());

    /**
     *
     */
    private static final ThreadLocal<List<Runnable>> RUNNABLES = new ThreadLocal<List<Runnable>>();

    /**
     *
     */
    @Autowired
    private Executor threadPoolExecutor;
    
    @Override
    public void execute(Runnable runnable) {
        LOGGER.info("Submitting new runnable {} to run after commit", runnable);
        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            LOGGER.info("Transaction synchronization is NOT ACTIVE. Executing right now runnable {}", runnable);
            runnable.run();
            return;
        }
        List<Runnable> threadRunnables = RUNNABLES.get();
        if (threadRunnables == null) {
            threadRunnables = new ArrayList<>();
            RUNNABLES.set(threadRunnables);
            TransactionSynchronizationManager.registerSynchronization(this);
        }
        threadRunnables.add(runnable);
    }

    @Override
    public void afterCommit() {
        List<Runnable> threadRunnables = RUNNABLES.get();
        LOGGER.info("Transaction successfully committed, executing {} runnables", threadRunnables.size());
        for (int i = 0; i < threadRunnables.size(); i++) {
            Runnable runnable = threadRunnables.get(i);
            LOGGER.info("Executing runnable {}", runnable);
            try {
                this.threadPoolExecutor.execute(runnable);
            } catch (RuntimeException e) {
                LOGGER.error("Failed to execute runnable " + runnable, e);
            }
        }
    }


}

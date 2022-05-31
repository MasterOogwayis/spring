package com.zsw.function;

import lombok.SneakyThrows;

/**
 * @author ZhangShaowei on 2022/5/31 11:54
 */
@FunctionalInterface
public interface ThrowableExecutor {

    /**
     * get
     *
     * @throws Throwable t
     */
    void apply() throws Throwable;


    /**
     * execute
     *
     * @throws RuntimeException e
     */
    @SneakyThrows
    default void execute() throws RuntimeException {
        apply();
    }


    /**
     * execute
     *
     * @param executor ThrowableExecutor
     */
    static void execute(ThrowableExecutor executor) {
        executor.execute();
    }

}

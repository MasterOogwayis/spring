package com.zsw.function;

import lombok.SneakyThrows;

/**
 * @author ZhangShaowei on 2022/2/15 15:57
 */
@FunctionalInterface
public interface ThrowableSupplier<T> {

    T get() throws Throwable;


    @SneakyThrows
    default T execute() throws RuntimeException{
        return get();
    }


    public static <R> R execute(ThrowableSupplier<R> supplier) {
        return supplier.execute();
    }

}

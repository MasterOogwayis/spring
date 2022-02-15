package com.zsw.function;

import lombok.SneakyThrows;

/**
 * @author ZhangShaowei on 2022/2/15 9:37
 */
@FunctionalInterface
public interface ThrowableFunction<T, R> {

    R apply(T t) throws Throwable;


    @SneakyThrows
    default R execute(T t) throws RuntimeException {
        return apply(t);
    }


    default <TT, TR> TR execute(TT t, ThrowableFunction<TT, TR> function) {
        return function.execute(t);
    }


}

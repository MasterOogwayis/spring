package com.zsw.io;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;

/**
 * @author ZhangShaowei on 2022/2/15 14:17
 */
@Slf4j
public class ShouldClose implements Closeable {
    @Override
    public void close() {
        log.info("close ... {}", System.currentTimeMillis());
    }
}

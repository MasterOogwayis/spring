package com.zsw.lesson.l6;

import lombok.AllArgsConstructor;

import javax.xml.namespace.QName;

/**
 * @author ZhangShaowei on 2021/4/12 16:05
 */
@AllArgsConstructor
public class TestCloseable implements AutoCloseable {
    private final String name;

    @Override
    public void close() throws Exception {
        throw new RuntimeException(name);
    }
}

package com.zsw.utils;

import lombok.SneakyThrows;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;

/**
 * @author ZhangShaowei on 2021/9/8 10:13
 */
public class MethodHandlesUtils {

    public static final int ALLOWED_MODES = Lookup.PRIVATE | Lookup.PROTECTED
            | Lookup.PACKAGE | Lookup.PUBLIC;

    @SneakyThrows
    public static Lookup lookup(Class<?> clazz) {
        Constructor<Lookup> constructor = Lookup.class.getDeclaredConstructor(Class.class, Class.class, int.class);
        constructor.setAccessible(Boolean.TRUE);
        return constructor.newInstance(clazz, null, ALLOWED_MODES);
    }

}

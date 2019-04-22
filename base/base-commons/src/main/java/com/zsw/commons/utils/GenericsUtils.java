package com.zsw.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型操作类.
 *
 */
@Slf4j
@SuppressWarnings("unchecked")
public class GenericsUtils {

    private GenericsUtils() {
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or <code>Object.class</code> if
     * cannot be determined
     */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or <code>Object.class</code> if
     * cannot be determined
     */
    public static Class getSuperClassGenricType(Class clazz, int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            log.debug(clazz.getSimpleName()
                    + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            log.warn("Index: " + index + ", Size of " + clazz.getSimpleName()
                    + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            log
                    .warn(clazz.getSimpleName()
                            + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class) params[index];
    }
}

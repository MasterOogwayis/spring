package com;

import lombok.SneakyThrows;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2020/3/23 11:25
 */
public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

    /**
     * @param source
     * @param targetName
     * @param type
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <T> T getField(Object source, String targetName, Class<T> type) {
        Assert.notNull(source, "source can not be null.");
        Class<?> clazz = source.getClass();
        Field field = findField(clazz, targetName, type);
        if (Objects.isNull(field)) {
            return null;
        }
        makeAccessible(field);
        return (T) field.get(source);
    }


    /**
     * 忽略 Object 里面的方法
     *
     * @param clazz
     * @return
     */
    public static List<Method> getMethods(Class clazz) {
        Assert.notNull(clazz, "Class must not be null");
        Class<?> searchType = clazz;
        List<Method> list = new ArrayList<>();
        while (searchType != null && searchType != Object.class) {
            Method[] methods = getDeclaredMethods(searchType);
            if (methods.length > 0) {
                list.addAll(Arrays.asList(methods));
            }
            searchType = searchType.getSuperclass();
        }
        return list;
    }


    public static Method[] getDeclaredMethods(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        Method[] result;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<Method> defaultMethods = findConcreteMethodsOnInterfaces(clazz);
        if (defaultMethods != null) {
            result = new Method[declaredMethods.length + defaultMethods.size()];
            System.arraycopy(declaredMethods, 0, result, 0, declaredMethods.length);
            int index = declaredMethods.length;
            for (Method defaultMethod : defaultMethods) {
                result[index] = defaultMethod;
                index++;
            }
        } else {
            result = declaredMethods;
        }
        return result;
    }

    private static List<Method> findConcreteMethodsOnInterfaces(Class<?> clazz) {
        List<Method> result = null;
        for (Class<?> ifc : clazz.getInterfaces()) {
            for (Method ifcMethod : ifc.getMethods()) {
                if (!Modifier.isAbstract(ifcMethod.getModifiers())) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(ifcMethod);
                }
            }
        }
        return result;
    }

}

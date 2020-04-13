package com;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2019/12/20 16:20
 **/
public class Strings {

    public static final String SUCCESS = "success";
    public static final String MINUS = "-";


    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String FAIL = "FAIL";

    public static final String SEMICOLON = ";";


    public static <E> String join(CharSequence delimiter, Collection<? extends E> collection, Function<? super E, String> function) {
        return collection.stream().map(function).collect(Collectors.joining(delimiter));
    }

    public static <E> String join(
            CharSequence delimiter, String prefix, String suffix, Collection<? extends E> collection, Function<? super E, String> function) {
        return collection.stream().map(function).collect(Collectors.joining(delimiter, prefix, suffix));
    }


}

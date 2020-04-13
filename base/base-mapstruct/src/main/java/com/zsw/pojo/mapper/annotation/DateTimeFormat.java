package com.zsw.pojo.mapper.annotation;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * yyyy-MM-dd HH:mm:ss
 *
 * @author ZhangShaowei on 2019/12/30 13:39
 **/
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface DateTimeFormat {
}

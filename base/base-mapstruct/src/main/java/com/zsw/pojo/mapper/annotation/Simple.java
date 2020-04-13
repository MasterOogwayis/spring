package com.zsw.pojo.mapper.annotation;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Qualifier 和 Spring 的 Qualifier 作用相同，都是用作限定作用域
 * 这个注解标记了 StandardMapper list -> list
 * 如果出现参数或返回值签名相同的方法，list to list 就会报错，发现2个模棱两可的方法
 * 这时候就需要指定一个转换 list 使用到的 one to one 方法。加上这个注解
 *
 *
 * 指定 IterableMapping
 * @author ZhangShaowei
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Simple {
}

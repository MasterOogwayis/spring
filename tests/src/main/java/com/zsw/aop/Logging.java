package com.zsw.aop;

import java.lang.annotation.*;

/**
 * @author Shaowei Zhang on 2022/3/20 19:59
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging {
}

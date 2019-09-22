package com.zsw.demo.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Timeout {

    long value();

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    String fallback() default "";

}

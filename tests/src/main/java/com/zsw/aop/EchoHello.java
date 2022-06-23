package com.zsw.aop;

import org.springframework.context.annotation.DependsOn;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EchoHello {
}

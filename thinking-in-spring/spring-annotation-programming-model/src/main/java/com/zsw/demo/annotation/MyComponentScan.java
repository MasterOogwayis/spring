package com.zsw.demo.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ComponentScan
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyComponentScan {

    @AliasFor(annotation = ComponentScan.class, value = "basePackages")
    String[] scan() default {};

    @AliasFor(value = "basePackageClasses")
    Class<?>[] clazz() default {};

    Class<?>[] basePackageClasses() default {};

}

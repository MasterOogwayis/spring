package com.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Administrator on 2019/9/16 22:05
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CustomComponemt {
}

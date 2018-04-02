package com.zsw.promethues.annotation;

import java.lang.annotation.*;

/**
 * interface
 *
 * @author ZhangShaowei on 2018/3/29 15:26
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrometheusMetrics {

    /**
     *  默认为空,程序使用method signature作为Metric name
     *  如果name有设置值,使用name作为Metric name
     * @return
     */
    String name() default "";

}

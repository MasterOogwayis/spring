package org.springframework.cache.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * TODO 自定义控制时间的注解需要 开启同名bean 覆盖 spring.main.allow-bean-definition-overriding: true 不然你得改全家桶代码
 *
 * @author ZhangShaowei on 2019/6/19 13:39
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ProxyCachingConfiguration.class))
@Import(CustomProxyCachingConfiguration.class)
@EnableCaching
public @interface EnableCustomCaching {
}

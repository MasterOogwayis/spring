package com.zsw.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ZhangShaowei
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EntityListeners(AuditingEntityListener.class)
public @interface Auditor {

    /**
     * @return The callback listener classes
     */
    @AliasFor(annotation = EntityListeners.class, value = "value")
    Class<?>[] value() default {};
}

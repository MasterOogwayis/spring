package com.zsw.base.cache.annotation;

import org.springframework.cache.annotation.AnnotationCacheOperationSource;

/**
 * @author ZhangShaowei on 2017/10/12 16:43
 */

public class CustomAnnotationCacheOperationSource extends AnnotationCacheOperationSource {

    /**
     *
     */
    public CustomAnnotationCacheOperationSource() {
        super(new CustomSpringCacheAnnotationParser());
    }
}

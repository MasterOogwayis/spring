package org.springframework.cache.interceptor;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/6/19 9:26
 **/
@Getter
@Setter
public class CustomCacheableOperation extends CacheableOperation {

    private Long expire;

    private TimeUnit timeUnit;

    /**
     * Create a new {@link CacheableOperation} instance from the given builder.
     *
     * @param b
     * @since 4.3
     */
    public CustomCacheableOperation(Builder b) {
        super(b);
        this.expire = b.expire;
        this.timeUnit = b.timeUnit;
    }

    /**
     * A builder that can be used to create a {@link CacheableOperation}.
     * @since 4.3
     */
    @Getter
    @Setter
    public static class Builder extends CacheableOperation.Builder {

        private Long expire;

        private TimeUnit timeUnit;


        @Override
        public CacheableOperation build() {
            return new CustomCacheableOperation(this);
        }
    }

}

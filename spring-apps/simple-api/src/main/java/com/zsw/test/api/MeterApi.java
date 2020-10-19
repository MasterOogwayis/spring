package com.zsw.test.api;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.lang.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author ZhangShaowei on 2020/10/15 14:23
 */
@RestController
@RequestMapping("meter")
public class MeterApi implements MeterBinder {

    protected Counter counter;

    @Value("${spring.application.name:}")
    private String appName;

    @GetMapping("incr")
    public Object incr() {
        this.counter.increment();
        return this.counter.count();
    }

    /**
     * @param meterRegistry
     */
    @Override
    public void bindTo(@NonNull MeterRegistry meterRegistry) {
        this.counter = Counter.builder("counter")
                .tag("label", "test")
                .tag("appName", this.appName)
                .description("test counter")
                .register(meterRegistry);
        // gauge 才能使用 delta 函数，范围内差值。适合这种检测即时错误
        meterRegistry.gauge(
                "mq.send.counter",
                Tags.concat(Collections.emptyList(), "appName", appName),
                this.counter,
                Counter::count
        );
    }
}

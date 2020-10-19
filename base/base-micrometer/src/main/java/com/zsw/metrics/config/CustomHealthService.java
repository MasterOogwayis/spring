package com.zsw.metrics.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhangShaowei on 2019/9/24 16:01
 **/
@Component
public class CustomHealthService extends AbstractHealthIndicator implements MeterBinder {

//    private MeterRegistry meterRegistry;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Value("${spring.application.name}")
    private String appName;

    private Counter counter;

    private Counter counter2;


    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        this.counter.increment();
        double number = this.counter.count();
//        int number = atomicInteger.getAndIncrement();
        if (number % 2 == 0) {
            builder.up();
        } else {
            builder.down();
        }
        builder.withDetail("number", number);
    }


    @Override
    public void bindTo(@NonNull MeterRegistry registry) {
        this.counter = Counter.builder("app_mq_send")
                .tag("label", "job1")
                .tag("appName", appName)
                .description("mq send success").register(registry);

        this.counter2 = Counter.builder("app_mq_send")
                .tag("label", "job2")
                .tag("appName", appName)
                .description("mq send faild").register(registry);

//        Gauge.builder("app_mq", this.atomicInteger, AtomicInteger::getAndIncrement)
//                .tag("name", "faild")
//                .description("mq send faild number")
//                .register(registry);
//
//        Gauge.builder("app_mq", this.atomicInteger, atomicInteger-> atomicInteger.getAndIncrement() + 1)
//                .tag("name", "success")
//                .description("mq send success number")
//                .register(registry);

//        this.meterRegistry.gauge(
//                "number",
//                Collections.singletonList(Tag.of("name", "number")),
//                this.atomicInteger,
//                AtomicInteger::getAndIncrement
//        );
    }
}

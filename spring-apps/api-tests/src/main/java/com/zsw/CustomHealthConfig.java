package com.zsw;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.autoconfigure.health.HealthContributorAutoConfiguration;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2019/9/24 16:01
 **/
@Configuration
@AutoConfigureBefore(HealthContributorAutoConfiguration.class)
@ConditionalOnEnabledHealthIndicator("checker")
public class CustomHealthConfig {

    @Bean("checker")
    public CustomHealthIndicator customHealthIndicator() {
        return new CustomHealthIndicator();
    }


    public static class CustomHealthIndicator extends AbstractHealthIndicator implements MeterBinder {

        @Value("${spring.application.name}")
        private String appName;

        private Counter counter;


        @Override
        protected void doHealthCheck(Health.Builder builder) throws Exception {
            this.counter.increment();
            double number = this.counter.count();
            if (number % 2 == 0) {
                builder.up();
            } else {
                builder.down();
            }
            builder.withDetail("number", number);
        }


        @Override
        public void bindTo(MeterRegistry registry) {
            this.counter = Counter.builder("checker")
                    .tag("label", "job1")
                    .tag("appName", appName)
                    .description("this is a test").register(registry);
        }
    }
}

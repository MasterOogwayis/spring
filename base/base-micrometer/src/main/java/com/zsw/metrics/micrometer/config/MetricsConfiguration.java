package com.zsw.metrics.micrometer.config;

import com.zsw.metrics.micrometer.CustomerMetricsHealthBean;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * endpoints.prometheus.sensitive=false 脱敏
 * configuration
 *
 * @author ZhangShaowei on 2018/3/30 14:53
 **/
//@Configuration
public class MetricsConfiguration {

    /**
     *
     */
    @Value("${spring.application.name}")
    private String appName;


//    /**
//     * 自定义标签
//     *
//     * @return
//     */
//    @Bean
//    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
//        return registry -> registry.config().commonTags("application", appName);
//    }

    /**
     * 自定义指标 将actuator health 指标追加到 Metrics
     *
     * @param meterRegistry
     * @param healthIndicators
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public CustomerMetricsHealthBean customerMetricsHealthBean(
            MeterRegistry meterRegistry, ObjectProvider<Map<String, HealthIndicator>> healthIndicators) {
        return new CustomerMetricsHealthBean(meterRegistry, healthIndicators);
    }

}

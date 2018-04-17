package com.zsw.metrics.micrometer.config;

import com.zsw.metrics.micrometer.CustomerMetricsHealthBean;
import com.zsw.metrics.micrometer.DiskSpaceHealthMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.DiskSpaceHealthIndicatorProperties;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * endpoints.prometheus.sensitive=false 脱敏
 * configuration
 *
 * @author ZhangShaowei on 2018/3/30 14:53
 **/
@Configuration
@ConditionalOnProperty(name = "endpoints.prometheus.enabled", havingValue = "true")
public class MetricsConfiguration {

    /**
     *
     */
    @Value("${spring.application.name}")
    private String appName;


    /**
     * 自定义标签
     *
     * @return
     */
    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", appName);
    }

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

    /**
     * 磁盘指标
     *
     * @param meterRegistry
     * @param properties
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DiskSpaceHealthMetrics diskSpaceHealthMetrics(
            MeterRegistry meterRegistry, DiskSpaceHealthIndicatorProperties properties) {
        return new DiskSpaceHealthMetrics(meterRegistry, properties);
    }

}

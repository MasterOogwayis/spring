package com.zsw.promethues.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * configuration
 *
 * @author ZhangShaowei on 2018/3/30 14:53
 **/
@Configuration
public class MetricsConfiguration {

    /**
     *
     */
    @Value("${spring.application.name}")
    private String appName;


    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", appName);
    }

}

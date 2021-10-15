package com.zsw.metrics.micrometer;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * SampleBean
 *
 * @author ZhangShaowei on 2018/4/4 11:03
 **/
@Component
public class CustomerMetricsHealthBean implements InitializingBean {

    @Autowired
    private MeterRegistry registry;

    @Autowired
    private Map<String, HealthIndicator> healthIndicators;

    public CustomerMetricsHealthBean(MeterRegistry registry, ObjectProvider<Map<String, HealthIndicator>> healthIndicators) {
        this.registry = registry;
        this.healthIndicators = new LinkedHashMap<>(healthIndicators.getIfAvailable());
    }

    @Override
    public void afterPropertiesSet() {
        this.healthIndicators.forEach((key, value) -> {
            // TODO 简单监控关联服务状态 只包含 UP=1 DOWN=0 详细信息在考虑or其他prefix
            // Prometheus requires that all meters with the same name have the same set of tag keys
            registry.gauge(
                    "health",
                    Collections.singletonList(Tag.of("name", getKey(key))),
                    value,
                    healthIndicator -> {
                        Health health = healthIndicator.health();
                        if (health.getStatus().equals(Status.UP)) {
                            return 1D;
                        }
                        return 0D;
                    });
        });
    }

    private String getKey(String name) {
        int index = name.toLowerCase().indexOf("healthindicator");
        if (index > 0) {
            return name.substring(0, index);
        }
        return name;
    }

}

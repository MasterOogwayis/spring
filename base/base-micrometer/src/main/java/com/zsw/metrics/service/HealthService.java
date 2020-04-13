package com.zsw.metrics.service;

import lombok.Setter;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2020/3/20 9:56
 */
@Setter
@Service
public class HealthService extends AbstractHealthIndicator {

    private volatile Integer num = 0;


    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (num % 2 == 0) {
            builder.up().withDetail("nameServer", "localhost:8080");
        } else  {
            builder.down();
        }
    }
}

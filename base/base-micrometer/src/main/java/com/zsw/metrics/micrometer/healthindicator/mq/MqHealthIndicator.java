package com.zsw.metrics.micrometer.healthindicator.mq;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * Mq HealthIndicator 自定义mq健康检查
 *
 * ribbit 等springboot提供的 mq sdk自带
 *
 * @author ZhangShaowei on 2018/4/12 10:04
 **/
public class MqHealthIndicator extends AbstractHealthIndicator {

    /**
     *
     */
    private final MqTemplate mqTemplate;

    public MqHealthIndicator(MqTemplate mqTemplate) {
        this.mqTemplate = mqTemplate;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("version", this.mqTemplate.getVersion());
    }
}

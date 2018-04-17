package com.zsw.metrics.micrometer.healthindicator.mq;

import org.springframework.boot.actuate.autoconfigure.CompositeHealthIndicatorConfiguration;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * CustomMqAutoConfiguration
 *
 * @author ZhangShaowei on 2018/4/12 10:55
 **/
@Configuration
public class CustomMqAutoConfiguration {

    /**
     * 自定义 mq 健康检查
     */
    @Configuration
    @ConditionalOnClass(MqTemplate.class)
    @ConditionalOnBean(MqTemplate.class)
    public static class CustomMqHealthIndicatorConfiguration extends
            CompositeHealthIndicatorConfiguration<MqHealthIndicator, MqTemplate> {

        private final Map<String, MqTemplate> mqTemplates;

//        public static final String NAME = "mqHealthIndicator";

        public CustomMqHealthIndicatorConfiguration(Map<String, MqTemplate> mqTemplates) {
            this.mqTemplates = mqTemplates;
//            int index;
//            String key = mqTemplates.keySet().iterator().next();
//            if ((index = key.toLowerCase().indexOf("template")) > 0) {
//                try {
//                    Field field = CustomMqHealthIndicatorConfiguration.class.getField("NAME");
//                    field.setAccessible(true);
//                    Field modifiersField = Field.class.getDeclaredField("modifiers");
//                    modifiersField.setAccessible(true);
//                    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
//                    field.set(null, key.substring(0, index) + "HealthIndicator");
//                    System.err.println("name: " + NAME);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }

        @Bean
        public HealthIndicator mqHealthIndicator() {
            return createHealthIndicator(this.mqTemplates);
        }

    }

}

package com.zsw.rabbit.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator on 2019/8/25 18:59
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "com.zsw.rabbitmq.exchange")
public class ZswRabbitExchangeProperties {

    private String directExchange;

    private String fanoutExchange;

    private String topicExchange;


}

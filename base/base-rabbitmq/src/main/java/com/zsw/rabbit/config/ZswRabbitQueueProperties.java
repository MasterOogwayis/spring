package com.zsw.rabbit.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator on 2019/8/25 19:11
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "com.zsw.rabbitmq.queue")
public class ZswRabbitQueueProperties {

    private String first;

    private String second;

    private String third;

    private String fourth;

}

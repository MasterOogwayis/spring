package com.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ZhangShaowei on 2017/5/3 13:37
 */
@Configuration
public class RabbitConfig {

    /**
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

}

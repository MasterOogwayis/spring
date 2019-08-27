package com.zsw.demo.utils;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @author ZhangShaowei on 2019/8/26 10:45
 **/
public class RabbitMQUtils {


    public static ConnectionFactory create() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.137.120");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        return factory;
    }


}

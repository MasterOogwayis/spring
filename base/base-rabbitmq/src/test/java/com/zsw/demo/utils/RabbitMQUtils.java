package com.zsw.demo.utils;

import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;

/**
 * @author ZhangShaowei on 2019/8/26 10:45
 **/
public class RabbitMQUtils {


    @SneakyThrows
    public static ConnectionFactory create() {
        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("192.168.137.120");
//        factory.setPort(5672);
//        factory.setVirtualHost("/");
//        factory.setUsername("guest");
//        factory.setPassword("guest");
        factory.setUri("amqp://guest:guest@192.168.137.120:5672");
        return factory;
    }


}

package com.zsw.rabbit.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsw.rabbit.config.ZswRabbitExchangeProperties;
import com.zsw.rabbit.config.ZswRabbitQueueProperties;
import com.zsw.rabbit.entity.Product;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Administrator on 2019/8/25 19:33
 **/
@Component
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ZswRabbitQueueProperties queueProperties;

    @Autowired
    private ZswRabbitExchangeProperties exchangeProperties;

    private static final Gson GSON = new GsonBuilder().create();

    public void send() {
        Product product = new Product(1L, "水果", 2.23D);
        this.rabbitTemplate.convertAndSend(exchangeProperties.getDirectExchange(), "zsw.test", product);

        this.rabbitTemplate.convertAndSend(exchangeProperties.getTopicExchange(), "chengdu.zsw.family", "topic message: Hello World!");
        this.rabbitTemplate.convertAndSend(exchangeProperties.getTopicExchange(), "moon.zsw.nothing", "topic message: Who's that?");

        this.rabbitTemplate.convertAndSend(exchangeProperties.getFanoutExchange(), "", GSON.toJson(product));


    }

}

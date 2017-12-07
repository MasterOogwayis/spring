package com.zsw.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2017/11/29 16:23
 */
@Configuration
@ConfigurationProperties(prefix = "zsw.base.mq.queue")
public class RabbitMQConfiguration {


    private String queueName;


    /**
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    /**
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    /**
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    SimpleMessageListenerContainer container(
            ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    /**
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }





    /**  */
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}

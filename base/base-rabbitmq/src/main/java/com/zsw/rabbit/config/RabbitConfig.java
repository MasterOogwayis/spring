package com.zsw.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator on 2019/8/25 18:59
 **/
@Configuration
@EnableConfigurationProperties({ZswRabbitExchangeProperties.class, ZswRabbitQueueProperties.class})
public class RabbitConfig {

    @Autowired
    private ZswRabbitExchangeProperties exchangeProperties;

    @Autowired
    private ZswRabbitQueueProperties queueProperties;


    /**
     * 创建三个交换机
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(this.exchangeProperties.getDirectExchange());
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(this.exchangeProperties.getFanoutExchange());
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(this.exchangeProperties.getTopicExchange());
    }


    /**
     * 创建四个队列
     *
     * @return
     */
    @Bean
    public Queue firstQueue() {
        return new Queue(this.queueProperties.getFirst());
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(this.queueProperties.getSecond());
    }

    @Bean
    public Queue thirdQueue() {
        return new Queue(this.queueProperties.getThird());
    }

    @Bean
    public Queue fourthQueue() {
        return new Queue(this.queueProperties.getFourth());
    }

    /**
     * 绑定交换机队列
     *
     * @param directExchange
     * @param queue
     * @return
     */
    @Bean
    public Binding firstBinding(DirectExchange directExchange, @Qualifier("firstQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("zsw.test");
    }

    @Bean
    public Binding secondBinding(TopicExchange topicExchange, @Qualifier("secondQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(topicExchange).with("*.zsw.*");
    }

    @Bean
    public Binding thirdBinding(FanoutExchange fanoutExchange, @Qualifier("thirdQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public Binding fourthBinding(FanoutExchange fanoutExchange, @Qualifier("fourthQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }


    /**
     * 自定义转 json
     *
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setAutoStartup(Boolean.TRUE);
        return factory;
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }


}

package com.zsw.demo.javaapi.returnlistener;

import com.rabbitmq.client.*;
import com.zsw.demo.utils.RabbitMQUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * 消息无法匹配到队列时，会回发给生产者
 *
 * @author ZhangShaowei on 2019/8/27 14:19
 **/
@Slf4j
public class ReturnListenerProducer {

    @SneakyThrows
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = RabbitMQUtils.create();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(
                    int replyCode, String replyText,
                    String exchange, String routingKey,
                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.warn("监听器收到无法路由，被返回的消息：replyText={}，exchange={}，routingKey={}，message={}",
                        replyText, exchange, routingKey, new String(body, Charset.forName("utf-8")));
            }
        });

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).
                contentEncoding("UTF-8").build();

        // 在声明交换机的时候指定备份交换机
        //Map<String,Object> arguments = new HashMap<String,Object>();
        //arguments.put("alternate-exchange","ALTERNATE_EXCHANGE");
        //channel.exchangeDeclare("TEST_EXCHANGE","topic", false, false, false, arguments);

        // 发送到了默认的交换机上，由于没有任何队列使用这个关键字跟交换机绑定，所以会被退回
        // 第三个参数是设置的mandatory，如果mandatory是false，消息也会被直接丢弃

        channel.basicPublish("", "zsw.return", true, properties, "这条消息我不收！".getBytes(Charset.forName("utf-8")));

        TimeUnit.SECONDS.sleep(10);

        channel.close();
        connection.close();

    }


}

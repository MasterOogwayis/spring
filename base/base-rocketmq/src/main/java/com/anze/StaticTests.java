package com.anze;

import com.aliyun.openservices.ons.api.bean.Subscription;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author ZhangShaowei on 2019/9/10 12:51
 **/
@Slf4j
public class StaticTests {


    public static void main(String[] args) {
        testConsumer();
    }

    public static void testConsumer() {
        Properties properties = getProperties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, "GID_BCPT_SC_TEST");
        properties.setProperty(PropertyKeyConst.ConsumeThreadNums, "5");
        properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);

        Consumer consumer = ONSFactory.createConsumer(properties);
//        Map<Subscription, MessageListener> map = new HashMap<>();
        Subscription subscription = new Subscription();
        subscription.setTopic("BCPT_SC_TEST");

//        map.put(subscription, new MessageListener() {
//            @Override
//            public Action consume(Message message, ConsumeContext context) {
//                return Action.ReconsumeLater;
//            }
//        });

        consumer.subscribe("BCPT_SC_TEST", "*", new MessageListener() {
            @Override
            public Action consume(Message message, ConsumeContext context) {
                log.info("received message : {}", new String(message.getBody()));
                return Action.ReconsumeLater;
            }
        });

        consumer.start();

        log.info("Consumer started ...");

    }


    public static void testProducer() {
        Properties properties = getProperties();
        properties.setProperty("InstanceName", "localhost");
        Producer producer = ONSFactory.createProducer(properties);
        producer.start();
        System.out.println("producer started ...");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while (true) {
                line = reader.readLine();
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }
                try {
                    SendResult send = producer.send(
                            new Message("BCPT_SC_TEST", "*", "asd".getBytes()));
                    System.out.println(send.getMessageId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }


    private static Properties getProperties() {
        Properties properties = new Properties();
//        properties.setProperty(PropertyKeyConst.GROUP_ID, "GID_BCPT_SC_TEST");
//        properties.setProperty(PropertyKeyConst.ProducerId, "GID_BCPT_SC_TEST");
//        properties.setProperty(PropertyKeyConst.ConsumerId, "GID_BCPT_SC_TEST");
        properties.setProperty(PropertyKeyConst.AccessKey, "LTAIxhbAKjb2ziY2");
        properties.setProperty(PropertyKeyConst.SecretKey, "Ybotwl4bPGWAF3PwN3EbnpfPYS6AqW");
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, "http://onsaddr.mq-internet-access.mq-internet.aliyuncs.com:80");
        return properties;
    }


//    public static void main(String[] args) {
//        Properties properties = new Properties();
//        properties.setProperty(PropertyKeyConst.GROUP_ID, "GID_BCPT_SC_TEST");
//        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
//        properties.put(PropertyKeyConst.AccessKey,"${AccessKey}");
//        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
//        properties.put(PropertyKeyConst.SecretKey, "${SecretKey}");
//        //设置发送超时时间，单位毫秒
//        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
//        // 设置 TCP 接入域名，到控制台的实例基本信息中查看
//        properties.put(PropertyKeyConst.NAMESRV_ADDR,
//                "http://onsaddr.mq-internet-access.mq-internet.aliyuncs.com:80");
//
//        Producer producer = ONSFactory.createProducer(properties);
//        // 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可
//        producer.start();
//
//        //循环发送消息
//        for (int i = 0; i < 100; i++){
//            Message msg = new Message( //
//                    // Message 所属的 Topic
//                    "${TOPIC}",
//                    // Message Tag 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在 MQ 服务器过滤
//                    "TagA",
//                    // Message Body 可以是任何二进制形式的数据， MQ 不做任何干预，
//                    // 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
//                    "Hello MQ".getBytes());
//            // 设置代表消息的业务关键属性，请尽可能全局唯一。
//            // 以方便您在无法正常收到消息情况下，可通过阿里云服务器管理控制台查询消息并补发
//            // 注意：不设置也不会影响消息正常收发
//            msg.setKey("ORDERID_" + i);
//
//            try {
//                SendResult sendResult = producer.send(msg);
//                // 同步发送消息，只要不抛异常就是成功
//                if (sendResult != null) {
//                    System.out.println(new Date() + " Send mq message success. Topic is:" + msg.getTopic() + " msgId is: " + sendResult.getMessageId());
//                }
//            }
//            catch (Exception e) {
//                // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
//                System.out.println(new Date() + " Send mq message failed. Topic is:" + msg.getTopic());
//                e.printStackTrace();
//            }
//        }
//
//        // 在应用退出前，销毁 Producer 对象
//        // 注意：如果不销毁也没有问题
//        producer.shutdown();
//    }

}

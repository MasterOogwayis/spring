package com.zsw.demo.rocketmq.simple;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.Set;

import static com.zsw.demo.rocketmq.RocketMQProperties.*;

/**
 * @author ZhangShaowei on 2019/8/30 13:37
 **/
@Slf4j
public class RocketMQConsumer {

    @SneakyThrows
    public static void main(String[] args) {

        testDefault();

    }


    /**
     * DefaultMQPushConsumer
     */
    private static void testDefault() throws Exception {
//        for (int i = 0; i < 2; i++) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(SIMPLE_GROUP);
        consumer.setNamesrvAddr(NAME_SERVER_ADDR);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//        consumer.subscribe(TOPIC, MessageSelector.bySql("a between 1 and 3"));
        consumer.subscribe(TOPIC, "tagtrue");
        consumer.setInstanceName("Consumer");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                msgs.forEach(msg -> {
                    log.info("consumer：{}, Thread: {}, message: {}",
                            "Consumer", Thread.currentThread().getName(), msg);
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
//        Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues(TOPIC);
//        }
    }


    private static void testPull() {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(SIMPLE_GROUP);
        consumer.setNamesrvAddr(NAME_SERVER_ADDR);


//        PullMessageRequestHeader header = new PullMessageRequestHeader();
//        header.setConsumerGroup(SIMPLE_GROUP);
//        header.setTopic(TOPIC);
//        header.setQueueId();
//        header.setQueueOffset();
//        header.setMaxMsgNums();
//        header.setSysFlag();
//        header.setCommitOffset();
//        header.setSuspendTimeoutMillis();
//        header.setSubscription();
//        header.setSubVersion();
//        header.setExpressionType();


    }


}

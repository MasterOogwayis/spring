package com.zsw.demo.rocketmq.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.zsw.demo.rocketmq.RocketMQProperties.*;

/**
 * @author Administrator on 2019/9/3 20:21
 **/
@Slf4j
public class PullConsumer {


    private static final Map<MessageQueue, Long> offsets = new ConcurrentHashMap<>();


    public static void main(String[] args) throws Exception {

        pull();

    }


    public static void pull() throws Exception {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(SIMPLE_GROUP);
        consumer.setNamesrvAddr(NAME_SERVER_ADDR);
        consumer.start();

        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues(TOPIC);
        for (MessageQueue mq : mqs) {
            log.info("Consume from the queue: {}", mq);
            SINGLE_MQ:
            while (true) {
                try {
                    PullResult pullResult =
                            consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                    log.info("pullResult:{}", pullResult);
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        consumer.shutdown();
    }

    private static void putMessageQueueOffset(MessageQueue messageQueue, long offset) {
        offsets.put(messageQueue, offset);
    }

    private static long getMessageQueueOffset(MessageQueue messageQueue) {
        return offsets.getOrDefault(messageQueue, 0L);
    }


}

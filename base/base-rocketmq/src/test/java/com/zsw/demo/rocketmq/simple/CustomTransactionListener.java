package com.zsw.lesson.rocketmq.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator on 2019/9/2 21:36
 **/
@Slf4j
public class CustomTransactionListener implements TransactionListener {

    private Map<String, Boolean> results = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        String orderId = o.toString();
        boolean result = Math.abs(Objects.hash(orderId)) % 2 == 0;
        results.put(orderId, result);
        log.info("开启本地事物：orderId={}, fail={}", orderId, result);
        return result ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        String orderId = messageExt.getKeys();
        Boolean result = results.getOrDefault(orderId, false);
        log.info("事务回调检查：orderId={}, result={}", orderId, result);
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}

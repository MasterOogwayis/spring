package com.zsw.base.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author ZhangShaowei on 2021/5/12 15:55
 */
public class PermissionsChangedEventPublisher implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public void publishEvent() {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            /**
             * 事物成功提交后 发送
             * 这是个阻塞方法 需异步
             */
            @Override
            public void afterCommit() {
                PermissionsChangedEventPublisher.this.applicationContext.publishEvent(
                        new PermissionsChangedEvent(PermissionsChangedEventPublisher.this.applicationContext));
            }
        });

    }
}

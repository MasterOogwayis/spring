package com.zsw.mq;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author ZhangShaowei on 2017/11/29 16:20
 */
@Component
public class Receiver {

    /**
     *
     */
    private CountDownLatch latch = new CountDownLatch(1);


    /**
     * @param message
     */
    public void receiveMessage(String message) {
        System.out.println("Received : " + message);
        latch.countDown();
    }

    /**  */
    public CountDownLatch getLatch() {
        return latch;
    }
}

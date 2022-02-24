package com.zsw.demo;

import com.zsw.mq.DemoConsumerService;
import org.springframework.core.ResolvableType;

/**
 * @author ZhangShaowei on 2022/2/24 11:51
 */
public class RocketMQListenerTests {

    public static void main(String[] args) {
        ResolvableType resolvableType = ResolvableType.forClass(DemoConsumerService.class);
        System.out.println(resolvableType.getInterfaces()[0].resolveGeneric(0));
    }

}

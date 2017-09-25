package com.zsw.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author ZhangShaowei on 2017/6/1 14:44
 */
@Component
public class CallDependencyService {

    /**
     *
     */
    private Random random = new Random();


    /**
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public String get() {
        int randomInt = random.nextInt(10);
        if (randomInt < 8) {  //模拟调用失败情况
            throw new RuntimeException("call dependency service fail.");
        } else {
            return "UserName:liaokailin;number:" + randomInt;
        }
    }

    /**
     * @return
     */
    public String fallback(){
        return "some exception occur call fallback method.";
    }

}

package com.zsw;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;


/**
 * @author ZhangShaowei on 2020/4/2 13:09
 */
public class UserHystrixCommand extends HystrixCommand<String> {

    private Long id;

    public UserHystrixCommand(Long id) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TestService"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("UserService"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        // 是否允许断路器工作
                                        .withCircuitBreakerEnabled(Boolean.TRUE)
                                        // 滑动窗口中，最少有多少个请求，才可能触发断路
                                        .withCircuitBreakerRequestVolumeThreshold(10)
                                        // 异常比例达到多少，才触发断路，默认50%
                                        .withCircuitBreakerErrorThresholdPercentage(40)
                                        // 断路后多少时间内直接reject请求，之后进入half-open状态，默认5000ms
                                        .withCircuitBreakerSleepWindowInMilliseconds(3 * 1000)
                        )
        );
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
        if (id < 20) {
            throw new Exception("失败");
        }
        return UserService.get(id);
    }

    @Override
    protected String getFallback() {
        return "fallBack";
    }
}

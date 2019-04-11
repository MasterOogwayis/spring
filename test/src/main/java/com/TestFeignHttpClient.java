package com;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author ZhangShaowei on 2019/4/4 16:50
 **/
@FeignClient(value = "http://localhost:80/test", fallback = TestFeignHttpClientHystrix.class)
public interface TestFeignHttpClient {

    public String sayHello(String name);
}

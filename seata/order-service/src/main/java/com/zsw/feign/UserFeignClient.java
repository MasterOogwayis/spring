package com.zsw.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhangShaowei on 2021/6/7 13:16
 */
@FeignClient(name = "account-service")
public interface UserFeignClient {


    /**
     * @param userId
     * @param money
     * @return
     */
    @GetMapping("/reduce")
    Boolean reduce(@RequestParam("userId") String userId, @RequestParam("money") int money);

}

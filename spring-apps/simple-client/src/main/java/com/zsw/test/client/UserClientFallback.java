package com.zsw.test.client;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @author ZhangShaowei on 2020/5/13 11:12
 */
@Component
public class UserClientFallback implements UserClient {
    @Override
    public Map<String, String> getUserInfo(String username) {
        return Collections.singletonMap("msg", "查询失败");
    }
}

package com.zsw.test.client;

import java.util.Collections;
import java.util.Map;

/**
 * @author ZhangShaowei on 2020/5/13 11:12
 */
public class UserClientFallback implements UserClient {
    @Override
    public Map<String, String> getUserInfo(String username) {
        return Collections.singletonMap("msg", "查询失败");
    }
}

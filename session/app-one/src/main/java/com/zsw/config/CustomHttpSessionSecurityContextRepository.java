package com.zsw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsw.utils.RedisHelper;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2021/7/9 15:01
 */
public class CustomHttpSessionSecurityContextRepository extends HttpSessionSecurityContextRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    private final String jsessionPrefix = "bbxbak_usercache_test_4.6.3_user_jsessionId_";
    private final String jsessionUser = "bbxbak_usercache_test_4.6.3_userPo_";

    public CustomHttpSessionSecurityContextRepository(
            RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        setAllowSessionCreation(Boolean.FALSE);
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        String sessionId = requestResponseHolder.getRequest().getSession().getId();
        String username = this.getSessionUsername(sessionId.replace("-",""));
        if (Objects.isNull(username)) {
            return SecurityContextHolder.createEmptyContext();
        }
        return new SecurityContextImpl(getFromRedis(username));
    }

    @SneakyThrows
    private Authentication getFromRedis(String username) {
        String json = this.redisTemplate.opsForValue().get(jsessionUser + username);
        Map map = objectMapper.readValue(json, Map.class);
        return new RedisAuthentication(map);
    }

    private String getSessionUsername(String sessionId) {
//        List<String> keys = new ArrayList<>();
//        RedisHelper.scan(jsessionPrefix+"*", 100, redisTemplate, keys::add);
        Set<String> keys = redisTemplate.keys(jsessionPrefix + "*");
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }
        List<String> collect = new ArrayList<>(keys);
        List<String> sessionIds = this.redisTemplate.opsForValue().multiGet(keys);
        for (int i = 0; i < sessionIds.size(); i++) {
            if (sessionId.equalsIgnoreCase(sessionIds.get(i))) {
                return collect.get(i).replace(jsessionPrefix, "");
            }
        }
        return null;
    }
}

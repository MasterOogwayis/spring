package com.zsw.orm.redis.configuration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZhangShaowei on 2019/6/20 11:08
 **/
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CacheConstsCustom implements CacheConsts {

    /**
     *
     */
    URSE_ROLES(UserKeys.KEY_USER_ROLES, DEFAULT_EXPIRE);

    /**
     * 设置 缓存的 key 命名空间，也是控制有效期的 key
     */
    private final String key;

    /**
     * 超时时间
     */
    private final Long expire;


    /**
     * 所有 CacheName定义到这里，注解里面使用Keys
     * 在 enum 里面配置 expire
     */
    public static class UserKeys {
        public static final String KEY_USER_ROLES = "namespace:user:roles";
    }

}

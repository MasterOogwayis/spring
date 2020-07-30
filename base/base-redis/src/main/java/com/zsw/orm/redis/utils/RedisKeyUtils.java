package com.zsw.orm.redis.utils;



import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 描      述    :
 *
 * @author : zhangshaowei
 */
public final class RedisKeyUtils {
    /**
     * 工具类
     */
    private RedisKeyUtils() {
    }


    /**
     * 描述：redis key计算方法---目前使用原始拼接
     * <p>
     * 命名规则 ---  项目名:模块名:功能名:关键字... 等  方便管理cache
     *
     * @param args 参数array
     * @return String
     * @author : zhangshaowei
     * @since : v1.0
     */
    public static String getKey(final String... args) {
        return "oc:" + StringUtils.join(Arrays.asList(args), ":");
    }


}

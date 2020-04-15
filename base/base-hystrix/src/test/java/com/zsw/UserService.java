package com.zsw;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;

/**
 * @author ZhangShaowei on 2020/4/2 13:43
 */
@DefaultProperties
public class UserService {

    public static String get(Long id) {
        return "user_" + id;
    }

}

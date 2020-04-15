package com.zsw;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2020/4/2 13:58
 */
@Service
@DefaultProperties
public class UserService {

    @HystrixCommand(fallbackMethod = "fallBack")
    public String getById(Long id) {
        return "User_" + id;
    }


    public String getByIdFallBack(Long id) {
        return "fallBack_" + id;
    }


}




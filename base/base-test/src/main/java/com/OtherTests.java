package com;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author ZhangShaowei on 2019/9/18 10:27
 **/
public class OtherTests {

    public static void main(String[] args) {
        System.out.println(FeignClient.class.getCanonicalName());
        System.out.println(FeignClient.class.getName());
    }

}

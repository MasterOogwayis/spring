package com.zsw.utils;

import java.util.UUID;

/**
 * @author ZhangShaowei on 2017/6/9 10:38
 */

public class RandomCodeUtils {

    /** */
    private RandomCodeUtils(){ }


    /**
     * 随机唯一key
     *
     * @return string
     */
    public static String randomCode(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}

package com.zsw.utils;

/**
 * @author ZhangShaowei on 2021/4/28 15:15
 */
public class ExceptionHandler {

    public static void handleException(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

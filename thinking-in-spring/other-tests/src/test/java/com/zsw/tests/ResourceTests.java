package com.zsw.tests;

import java.util.ListResourceBundle;

/**
 * @author ZhangShaowei on 2021/6/22 9:14
 */
public class ResourceTests {

    public static void main(String[] args) {
        ListResourceBundle resourceBundle = new MyResourceBundle();
        String message = resourceBundle.getString("message");
        System.out.println(message);
    }

}

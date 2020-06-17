package com.zsw;

import com.zsw.commons.support.Duplicate;

/**
 * @author ZhangShaowei on 2020/6/17 19:17
 */
public class SimpleTests {

    public static void main(String[] args) {

        Duplicate.checkDuplicate("application.yml");
        Duplicate.checkDuplicate("META-INF/spring.factories");

    }

}

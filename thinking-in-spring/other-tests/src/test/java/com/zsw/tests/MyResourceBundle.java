package com.zsw.tests;

import java.util.ListResourceBundle;

/**
 * @author ZhangShaowei on 2021/6/22 9:15
 */
public class MyResourceBundle extends ListResourceBundle {

    private static final Object[][] contents;

    static {
        contents = new Object[1][2];
        contents[0] = new Object[]{"message", "Hello \"{1}\""};
    }

    @Override
    protected Object[][] getContents() {
        return new Object[0][];
    }
}

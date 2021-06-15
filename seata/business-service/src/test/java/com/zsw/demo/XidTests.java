package com.zsw.demo;

import io.seata.core.context.RootContext;

/**
 * @author ZhangShaowei on 2021/6/15 10:38
 */
public class XidTests {

    public static void main(String[] args) {
        String xid = RootContext.getXID();
    }

}

/**
 * 文 件 名：BaseConstant.java
 * CopyRight (c) 2012 Anze, Inc. All rights reserved.
 * 创 建 人：lurf
 * 日      期：2012-8-24 上午11:32:55
 * 描      述：
 */
package com.zsw.base.constant;

import java.util.Properties;

/**
 * @author ZhangShaowei on 2017/9/8 16:24
 */
public final class BaseConstant {

    /**
     *
     */
    private BaseConstant() {
    }

    /**
     * 是否是Debug模式，true：是，false：否
     */
    public static final boolean IS_DEBUG;

    /**
     * 默认时间戳格式：'yyyy-MM-dd HH:mm:ss'
     */
    public static final String TIMESTAMP_FORMAT;

    static {
        Properties properties = ConstantLoader.loadProperties("base-constant");

        if (null == properties) {
            IS_DEBUG = true;
            TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
        } else {
            String value = properties.getProperty("base.isDebug", "true");
            IS_DEBUG = Boolean.valueOf(value);

            value = properties.getProperty("base.timestampFormat", "yyyy-MM-dd HH:mm:ss");
            TIMESTAMP_FORMAT = value;
        }
    }
}

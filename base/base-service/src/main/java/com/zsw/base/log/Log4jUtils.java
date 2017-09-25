package com.zsw.base.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ZhangShaowei on 2017/9/8 16:11
 */

public final class Log4jUtils {

    /** */
    private Log4jUtils() {
    }

    /**
     * 描述：获取基本Log对象(log4j.xml里配置的root日志)
     *
     * @return Log
     */
    public static Log getLog() {
        return LogFactory.getLog(Log4jUtils.class);
    }

    /**
     * 描述：获取基本Log对象
     *
     * @param clazz Class
     * @return Log
     */
    public static Log getLog(final Class<?> clazz) {
        return LogFactory.getLog(clazz);
    }

    /**
     * 描述：根据日志名称获取Log对象
     *
     * @param loggerName 日志名称
     * @return Log
     */
    public static Log getLog(final String loggerName) {
        return LogFactory.getLog(loggerName);
    }
}

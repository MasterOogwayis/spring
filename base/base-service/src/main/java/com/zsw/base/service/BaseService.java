package com.zsw.base.service;

import ch.qos.logback.classic.Logger;
import com.zsw.base.utils.JacksonSerializer;
import org.slf4j.LoggerFactory;

/**
 * @author ZhangShaowei on 2017/9/8 16:24
 */

public class BaseService extends JacksonSerializer {

    /**
     *
     */
    protected final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

}

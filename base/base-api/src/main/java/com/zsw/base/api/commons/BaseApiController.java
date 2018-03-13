package com.zsw.base.api.commons;

import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ZhangShaowei on 2017/5/9 10:32
 */

public class BaseApiController {

    /**
     *
     */
    protected final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());


    /**
     *
     */
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    /**
     * @param ex exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    protected Object exceptionHandler(Exception ex) {
        ex.printStackTrace();
        return ex;
    }

}

package com.api;

import com.zsw.base.redis.dao.commons.BaseCacheDao;
import com.zsw.mq.persistence.bean.MessageAddress;
import com.zsw.mq.service.MessageAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangShaowei on 2018/1/12 14:48
 */
@RestController
@RequestMapping("dev")
public class ApiController {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     */
    @Autowired
    private MessageAddressService messageAddressService;

    /**
     *
     */
    @Autowired
    private BaseCacheDao cache;

    /**
     * @param messageAddress
     * @return
     */
    @PostMapping("add")
    public MessageAddress add(@ModelAttribute final MessageAddress messageAddress) {
        return this.messageAddressService.saveOrUpdate(messageAddress);
    }

    /**
     * @param msg
     * @return
     */
    @GetMapping("log")
    public String log(String msg) {
        this.logger.error("error: " + msg);
        this.logger.warn("warn: " + msg);
        this.logger.debug("debug: " + msg);
        this.logger.info("info: " + msg);
        return "{\"success\": true}";
    }

}

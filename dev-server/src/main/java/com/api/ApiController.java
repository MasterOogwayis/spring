package com.api;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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
//    @Autowired
//    private MessageAddressService messageAddressService;
//
//    /**
//     *
//     */
//    @Autowired
//    private BaseCacheDao cache;

    /**
     * @param messageAddress
     * @return
     */
//    @PostMapping("add")
//    @ApiOperation("添加地址")
//    public MessageAddress add(@ModelAttribute final MessageAddress messageAddress) {
//        return this.messageAddressService.saveOrUpdate(messageAddress);
//    }

    /**
     * @param msg
     * @return
     */
    @GetMapping("log")
    @ApiOperation("日志输出")
    public String log(String msg) {
        this.logger.error("message error: " + msg);
        this.logger.warn("message warn: " + msg);
        this.logger.debug("message debug: " + msg);
        this.logger.info("message info: " + msg);

        List<String> list = Arrays.asList("1", "2", "3", msg);

        this.logger.info("list: {}", list);

        return "{\"success\": true}";
    }


}

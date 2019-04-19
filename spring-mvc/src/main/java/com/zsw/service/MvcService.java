package com.zsw.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2019/4/18 17:22
 **/
@Slf4j
@Lazy
@Service
public class MvcService {

    public void sayHello(String message) {
        log.info("Hello {}", message);
    }


}

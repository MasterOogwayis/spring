package com.zsw.aop;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Shaowei Zhang on 2022/3/16 21:00
 **/
public interface HelloInterface {
    @Transactional
    @EchoHello
    @GetMapping
    String hello();
}

package com.zsw.demo.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

/**
 * @author ZhangShaowei on 2019/11/8 15:23
 **/
@RequestMapping("address")
@RestController
public class AddressController {

    @GetMapping
    @SneakyThrows
    public Object ip() {
        return InetAddress.getLocalHost().getHostAddress();
    }

}

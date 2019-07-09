package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@Slf4j
@RequestMapping("log")
@RestController
public class SimpleController {

    @GetMapping
    public Object log() {
        String data = "阿萨德啊苏打苏打岸上打东三省大神大神大神达到阿萨德阿萨德阿萨德阿萨德啊苏打苏打岸上打东三省大" +
                "神大神大神达到阿萨德阿萨德阿萨德阿萨德啊苏打苏打岸上打东三省大神大神大神达到阿萨德阿萨德阿萨德阿" +
                "萨德啊苏打苏打岸上打东三省大神大神大神达到阿萨德阿萨德阿萨德阿萨德啊苏打苏打岸上打东三省大神大神" +
                "大神达到阿萨德阿萨德阿萨德阿萨德啊苏打苏打岸上打东三省大神大神大神达到阿萨德阿萨德阿萨德阿萨德啊" +
                "苏打苏打岸上打东三省大神大神大神达到阿萨德阿萨德阿萨德阿萨德啊苏打苏打岸上打东三省大神大神大神达" +
                "到阿萨德阿萨德阿萨德阿萨德啊苏打苏打岸上打东三省大神大神大神达到阿萨德阿萨德阿萨德阿萨德啊苏打苏" +
                "打岸上打东三省大神大神大神达到阿萨德阿萨德阿萨德阿萨德啊苏打苏打岸上打东三省大神大神大神达到阿萨" +
                "德阿萨德阿萨德阿萨德啊苏打苏打岸上打东三省大神大神大神达到阿萨德阿萨德阿萨德阿萨德啊苏打苏打岸上" +
                "打东三省大神大神大神达到阿萨德阿萨德阿萨德阿萨德啊苏打苏打岸上打东三省大神大神大神达到阿萨德阿萨" +
                "德阿萨德";
        byte[] data1 = new byte[2 * 1024 * 1024];
        log.debug("data={}, data1={}", data, data1);
        return "success";
    }


}

package com.zsw;

import com.zsw.client.FcActuatorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/7/7 10:56
 */
@RestController
@RequestMapping("fc")
public class FcActuatorController {

    @Autowired
    private FcActuatorClient fcClient;


    @GetMapping("health")
    public Object health() {
        return this.fcClient.health();
    }

    @GetMapping("prometheus")
    public Object prometheus() {
        return this.fcClient.prometheus();
    }

}

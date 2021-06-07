package com.zsw.business;

import com.zsw.rabbit.producer.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator on 2019/8/25 19:36
 **/
@RequestMapping("rabbit")
@RestController
public class RabbitController {


    @Autowired
    private RabbitSender sender;

    @GetMapping("send")
    public String send() {
        this.sender.send();
        return "success";
    }

}

package com.demo.web;

import com.demo.service.ComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/4/26 14:54
 */
@RestController
public class ConsumerController {

    /**
     *
     */
    @Autowired
    private ComputeService computeService;


    /**
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add() {
        return this.computeService.add();
    }





}

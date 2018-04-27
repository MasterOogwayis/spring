package com.controller;

import com.zsw.base.ui.commons.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo
 *
 * @author ZhangShaowei on 2018/4/25 14:23
 **/
@RestController
public class SimpleController extends BaseController {

    @GetMapping("work")
    public String work(String msg) {
        return "work: " + msg;
    }


}

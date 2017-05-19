package com.example.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author ZhangShaowei on 2017/5/10 18:09
 */
@Controller
@EnableWebMvc
public class HelloController {
    @Autowired
    private CommonQueryClient commonQueryClient;

    /**
     * @return
     */
    @GetMapping("/")
    public
    @ResponseBody
    String index() {
        return "Hello World!";
    }


    /**
     * @return
     */
    @PostMapping("/config")
    public
    @ResponseBody
    CommonQueryConfigDTO get() {
        return this.commonQueryClient.getCheckedCommonQueryConfig("queryConfig", null, null);
    }


    /**
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "helloJsp";
    }

}

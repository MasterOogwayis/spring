package com.zsw;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/12/7 10:09
 */
@RestController
public class TestController {

    /**
     *
     */
    private String form = "form";

    /**
     * @return
     */
    @GetMapping("form")
    public String increment() {

        return this.form;

    }


}

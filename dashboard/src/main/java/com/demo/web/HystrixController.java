package com.zsw.web;

import com.zsw.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/6/1 14:43
 */
@RestController
public class HystrixController {

    /**
     *
     */
    @Autowired
    private HystrixService hystrixService;

    /**
     * @return
     */
    @PostMapping("/call")
    public String callDependencyService() {
        return this.hystrixService.callDependencyService();
    }


}

package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2017/6/1 14:43
 */
@Service
public class HystrixService {

    /**
     *
     */
    @Autowired
    private CallDependencyService dependencyService;

    /**
     * @return
     */
    public String callDependencyService() {
        return this.dependencyService.get();
    }


}

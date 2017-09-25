package com.zsw.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhangShaowei on 2017/4/28 13:51
 */
@Component
public class ComputeClientHystrix implements ComputeClient{

    /**
     * @param a int
     * @param b int
     * @return
     */
    @Override
    public Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        return -9999;
    }

    /**
     * @param name string
     * @return
     */
    @Override
    public String hi(@RequestParam("name") String name) {
        return "error";
    }

}

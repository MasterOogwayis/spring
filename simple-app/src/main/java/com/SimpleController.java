package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@Slf4j
@RequestMapping("test")
@RestController
public class SimpleController {


    @GetMapping("{path}")
    public Object get(@PathVariable("path") String path) {
        return path;
    }

    @PostMapping("shutdown")
    public Object post(@RequestParam("timerout") Integer timerout) throws InterruptedException {
        for (int i = timerout; i > 0; i--) {
            TimeUnit.SECONDS.sleep(1);
            log.info("处理时间剩余 ： {}s", i);
        }
        return "success";
    }


}

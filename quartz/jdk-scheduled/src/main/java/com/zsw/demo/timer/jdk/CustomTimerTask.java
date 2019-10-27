package com.zsw.demo.timer.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * @author Administrator on 2019/10/27 14:21
 **/
@Slf4j
public class CustomTimerTask extends TimerTask {
    @Override
    public void run() {
        log.info("running task ...");
    }
}

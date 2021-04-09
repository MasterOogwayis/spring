package com.zsw.lesson.timer.jdk;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator on 2019/10/27 14:23
 **/
@Slf4j
public class TimerTests {

    @SneakyThrows
    public static void main(String[] args) {
        Timer timer = new Timer();
        CustomTimerTask timerTask = new CustomTimerTask();
        timer.schedule(timerTask, 5000, 1000);

        TimeUnit.SECONDS.sleep(20);
        timer.cancel();
    }

}

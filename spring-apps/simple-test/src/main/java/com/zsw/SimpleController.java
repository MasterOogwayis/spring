package com.zsw;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/11/28 16:46
 **/
@RestController
@RequestMapping("test")
public class SimpleController {


    @GetMapping
    public Object test() {
        TestThread testThread = new TestThread(System.currentTimeMillis());

        new Thread(testThread).start();


        return "success";
    }



    @AllArgsConstructor
    class TestThread implements Runnable {

        private final long timeout = TimeUnit.MINUTES.toMillis(5);

        private final long timer;

        @Override
        public void run() {

            while (System.currentTimeMillis() - timer < timeout) {
                String str = "1" + "2" + "3";
            }


        }
    }


}

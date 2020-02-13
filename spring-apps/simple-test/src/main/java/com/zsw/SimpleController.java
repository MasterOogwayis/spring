package com.zsw;

import com.zsw.pojo.Mail;
import com.zsw.pojo.Sms;
import com.zsw.queue.MailQueue;
import com.zsw.queue.SmsQueue;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/11/28 16:46
 **/
@RestController
@RequestMapping
public class SimpleController {

    @Autowired
    private MailQueue mailQueue;

    @Autowired
    private SmsQueue smsQueue;

    @SneakyThrows
    @GetMapping("pollSms")
    public Object pollSms(@RequestParam Long second) {
        Sms poll = this.smsQueue.poll(second, TimeUnit.SECONDS);
        return poll;
    }


    @GetMapping("sms")
    public Object sms() {
        return this.smsQueue.offer(Sms.builder().phoneNumber("18380160980").content("Hello World!").build());
    }

    @GetMapping("pollMail")
    public Object pollMail() {
        Mail poll = this.mailQueue.poll();
        return poll;
    }


    @GetMapping("mail")
    public Object mail() {
        return this.mailQueue.offer(
                Mail.builder()
                        .mailAddress("123@qq.com")
                        .content("Hello World!")
                        .attachmentPath("/data/bcpt/1.txt")
                        .build()
        );
    }


}

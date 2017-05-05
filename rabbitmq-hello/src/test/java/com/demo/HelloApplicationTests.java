package com.demo;

import com.RabbitApplication;
import com.rabbit.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ZhangShaowei on 2017/5/3 13:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class HelloApplicationTests {

    @Autowired
    private Sender sender;


    /**
     *
     */
    @Test
    public void hello() throws Exception{
        sender.send();
    }

}

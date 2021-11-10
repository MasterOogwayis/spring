package com.zsw.demo.component2;

import com.zsw.demo.app.Parent;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/11/10 10:05
 */
@Component
public class B implements Parent {

    @Override
    public String hello() {
        return "This is B.";
    }
}

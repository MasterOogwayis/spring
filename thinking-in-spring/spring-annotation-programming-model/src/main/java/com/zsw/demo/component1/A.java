package com.zsw.demo.component1;

import com.zsw.demo.app.Parent;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/11/10 10:05
 */
@Component
public class A implements Parent {

    @Override
    public String hello() {
        return "This is A.";
    }
}

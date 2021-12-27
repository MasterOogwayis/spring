package com.zsw.web;

import com.zsw.autowired.NameHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/12/27 9:34
 */
@AllArgsConstructor
@RequestMapping("names")
@RestController
public class NameApi {

    private final NameHandler handler;


    @GetMapping
    public Object names() {
        return this.handler.getNames();
    }


}

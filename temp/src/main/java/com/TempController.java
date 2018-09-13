package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2018/9/5 16:18
 **/
@RestController
@RequestMapping("/temp")
public class TempController {

    @GetMapping("first")
    public String first(){
        return "first";
    }

    @GetMapping("second")
    public String second(){
        return "second";
    }


}

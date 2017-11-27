package com;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/11/17 10:52
 */
@RestController
public class TestController {


    /**
     * @param requestDto
     * @return
     */
    @PostMapping("test")
    public RequestDto test(@RequestBody RequestDto requestDto) {
        return requestDto;
    }


}

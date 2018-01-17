package com.zsw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/12/7 10:09
 */
@RestController
public class IndexController {


    /**
     * @return
     */
    @GetMapping("index")
    public String index() {
        return "index";
    }

//    /**
//     * @param principal
//     * @return
//     */
//    @GetMapping("user")
//    public Principal user(Principal principal) {
//        return principal;
//    }


}

package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author ZhangShaowei on 2017/12/26 9:46
 */
@RestController
public class IndexController {

    @GetMapping("index")
    public String index() {
        return "index";
    }


    /**
     * @param principal
     * @return
     */
    @GetMapping("user")
    public Principal user(Principal principal) {
        return principal;
    }


}

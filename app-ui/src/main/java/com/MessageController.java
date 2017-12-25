package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ZhangShaowei on 2017/12/22 13:12
 */
@Controller
public class MessageController {

    /**
     * @param model
     * @return
     */
    @GetMapping("msg")
    public String index(Model model) {
        model.addAttribute("message", "This is a test file.");

        return "message";
    }


}

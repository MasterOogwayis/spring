package com.zsw.temp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

/**
 * @author ZhangShaowei on 2020/12/16 11:43
 */
@Slf4j
@Controller
public class WeixinController {


    private static final String ADDRESS = "Earth";


    public static void main(String[] args) {
        System.out.println(ADDRESS);
    }

}

package com.zsw.autowired;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/12/27 9:32
 */
@Component
public class StringNameCreator {


    public List<String> names() {
        return Arrays.asList("1", "2", "3");
    }


}

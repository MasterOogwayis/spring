package com;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/4/3 13:53
 **/
public class StaticTests {


    private static final long USER_INFO_CACHE_TIMER = TimeUnit.DAYS.toSeconds(3);

    public static void main(String[] args) throws Exception {



        System.out.println(USER_INFO_CACHE_TIMER);

    }


}

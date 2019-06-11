package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/4/3 13:53
 **/
public class StaticTests {


    private static final long USER_INFO_CACHE_TIMER = TimeUnit.DAYS.toSeconds(3);

    private static final Gson gson = new GsonBuilder().create();

    public static void main(String[] args) throws Exception {

        List<String> list = null;
        System.out.println(CollectionUtils.get(list, 0));


    }


}

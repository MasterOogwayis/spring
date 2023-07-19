package com.tests;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * @author zhangshaowei on 2023/7/19 14:23
 */
public class SortTest {

    public static void main(String[] args) {
        // 中文拼音排序
        Collator collator = Collator.getInstance(Locale.CHINA);


        List<String> list = new ArrayList<>();
        list.add("张小");
        list.add("李四");
        list.add("王五");
        list.add("张大");
        list.add("王六");
        list.add("老六");

        list.sort(collator::compare);

        list.forEach(System.out::println);

    }

}

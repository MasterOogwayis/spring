package com.zsw;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Arrays;

/**
 * @author ZhangShaowei on 2021/12/6 16:49
 */
public class HtmlTests {

    public static void main(String[] args) {
        String html = "<div><span style=\"color: red;font-family: 仿宋;\" class=\"tag\">key</span>123</div>";
        Document doc = Jsoup.parseBodyFragment(html);
        System.out.println(doc);


        System.err.println(Arrays.asList("".split("\n")).size());
    }

}

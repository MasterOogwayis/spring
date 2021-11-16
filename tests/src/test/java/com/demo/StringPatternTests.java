package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2021/11/12 9:43
 */
@Slf4j
public class StringPatternTests {


    public static void main(String[] args) {
        test();
    }

    private static void test() {
        String text = "${key,键}，这是一个测试。${value,值}}},这是个错误示范 ${},姓名：${value,值1}";
        // 需要替换的字符串 ${key} -> value
        Map<String, String> needReplace = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        data.put("key", "name");
        data.put("value", "张三");
        PatternHelper.findKey(text, key -> {
            if (StringUtils.isBlank(key)) {
                return;
            }
            needReplace.computeIfAbsent(key, k -> {
                // key = key,desc
                String[] split = k.split(",");
                return data.get(split[0]);
            });
        });
        String html = text;

        for (Map.Entry<String, String> entry : needReplace.entrySet()) {
            html = html.replaceAll("\\$\\{" + entry.getKey() + "}", entry.getValue());
        }
        System.out.println(html);
    }


}
